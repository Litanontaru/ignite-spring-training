package com.epam.training;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.transactions.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Andrei_Yakushin
 */
public class ContextProviderImpl implements ContextProvider {
    private static final String CACHE_NAME = "plugins";
    private static final String CACHE_KEY = "plugins";

    @Autowired
    private Ignite ignite;

    @Autowired
    private ApplicationContext parent;

    private final Map<String, ApplicationContext> localContextCache = new ConcurrentHashMap<>();

    @Override
    public void register(String path) {
        try (Transaction tx = ignite.transactions().txStart()) {
            IgniteCache<String, Set<String>> cache = ignite.cache(CACHE_NAME);
            Set<String> paths = cache.get(CACHE_KEY);
            if (paths == null) {
                paths = new HashSet<>();
            }
            paths.add(path);
            cache.put(CACHE_KEY, paths);
            tx.commit();
        }
    }

    @Override
    public ApplicationContext get(String path) {
        Set<String> paths = ignite.<String, Set<String>>cache(CACHE_NAME).get(CACHE_KEY);
        if (paths.contains(path)) {
            return localContextCache.computeIfAbsent(path, this::create);
        } else {
            throw new IllegalArgumentException("Unknown path " + path);
        }
    }

    private ApplicationContext create(String path) {
        return new ClassPathXmlApplicationContext(new String[]{path}, true, parent);
    }
}
