package com.epam.training;

/**
 * @author Andrei_Yakushin
 */
public interface ContextProvider {
    void register(String path, String bean);

    <T> T get(String bean);
}
