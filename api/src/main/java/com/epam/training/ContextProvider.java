package com.epam.training;

import org.springframework.context.ApplicationContext;

/**
 * @author Andrei_Yakushin
 */
public interface ContextProvider {
    void register(String path);

    ApplicationContext get(String path);
}
