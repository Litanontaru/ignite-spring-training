package com.epam.training;

import org.apache.ignite.Ignition;

/**
 * @author Andrei_Yakushin
 */
public class Client {
    public static void main(String[] args) {
        Ignition.start("ignite-cfg.xml");
    }
}
