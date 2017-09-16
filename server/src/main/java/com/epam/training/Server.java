package com.epam.training;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Andrei_Yakushin
 */
public class Server {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("ignite-cfg.xml");
    }
}
