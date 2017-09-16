package com.epam.training;

import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Consumer;

/**
 * @author Andrei_Yakushin
 */
public class PutBean implements Consumer<String> {
    @Autowired
    private Ignite ignite;

    @Override
    public void accept(String value) {
        ignite.cache("cache").put("key", value);
        System.out.println("Put to cache " + value);
    }
}