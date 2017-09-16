package com.epam.training;

import org.apache.ignite.Ignite;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.ignite.resources.SpringApplicationContextResource;
import org.apache.ignite.services.ServiceContext;
import org.springframework.context.ApplicationContext;

/**
 * @author Andrei_Yakushin
 */
public class HelloServiceImpl implements HelloService {
    @IgniteInstanceResource
    private transient Ignite ignite;

    @SpringApplicationContextResource
    private transient ApplicationContext applicationContext;

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
