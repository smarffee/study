package com.lin.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ConsumerMainApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("consumer.xml");

        UserService userService = context.getBean(UserService.class);
        userService.getUserAddressList("sdf");
    }

}
