package com.epam.training;

import org.apache.ignite.services.ServiceContext;

/**
 * @author Andrei_Yakushin
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void cancel(ServiceContext ctx) {
        System.out.println("HelloServiceImpl.cancel");
    }

    @Override
    public void init(ServiceContext ctx) throws Exception {
        System.out.println("HelloServiceImpl.init");
    }

    @Override
    public void execute(ServiceContext ctx) throws Exception {
        System.out.println("HelloServiceImpl.execute");
    }
}
