package com.yubin.spring.di.businesslogic;

import com.yubin.spring.di.config.InjectByType;

public class ConsoleAnnouncer implements Announcer {
    @InjectByType
    private Recommendator recommendator;
    @Override
    public void announce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }
}
