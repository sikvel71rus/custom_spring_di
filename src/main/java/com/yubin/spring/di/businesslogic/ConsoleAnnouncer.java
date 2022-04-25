package com.yubin.spring.di.businesslogic;

import com.yubin.spring.di.config.ObjectFactory;

public class ConsoleAnnouncer implements Announcer {
    private Recommendator recommendator = ObjectFactory.getInstance().createObject(Recommendator.class);
    @Override
    public void announce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }
}
