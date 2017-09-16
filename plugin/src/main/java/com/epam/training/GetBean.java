package com.epam.training;

import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Consumer;

/**
 * @author Andrei_Yakushin
 */
public class GetBean implements Consumer<String> {
    @Autowired
    private Ignite ignite;

    @Override
    public void accept(String value) {
        Object old = ignite.cache("cache").get("key");
        System.out.println(old + " " + value);
    }
}
