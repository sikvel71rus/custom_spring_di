package com.yubin.spring.di.businesslogic;

import com.yubin.spring.di.config.InjectByType;

import javax.annotation.PostConstruct;

public class LawDocumentChecker implements DocumentChecker {
    @InjectByType
    private Recommendator recommendator;

    @PostConstruct
    public void init() {
        System.out.println(recommendator.getClass());
    }

    @Override
    public void checkDocumentAvailability() {
        System.out.println("Документы проверены на законность!");
        recommendator.recommend();
    }
}
