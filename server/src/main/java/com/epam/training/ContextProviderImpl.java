package com.epam.training;

import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Andrei_Yakushin
 */
public class ContextProviderImpl implements ContextProvider {
    private static final String CACHE_NAME = "plugins";

    @Autowired
    private Ignite ignite;

    @Autowired
    private ApplicationContext parent;

    private final Map<String, ApplicationContext> localContextCache = new ConcurrentHashMap<>();

    @Override
    public void register(String path, String bean) {
        ignite.cache(CACHE_NAME).put(bean, path);
    }

    @Override
    public <T> T get(String bean) {
        String path = ignite.<String, String>cache(CACHE_NAME).get(bean);
        return (T) localContextCache.computeIfAbsent(path, this::create).getBean(bean);
    }

    private ApplicationContext create(String path) {
        return new ClassPathXmlApplicationContext(new String[]{path}, true, parent);
    }
}
