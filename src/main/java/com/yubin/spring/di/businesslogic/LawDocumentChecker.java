package com.yubin.spring.di.businesslogic;

import com.yubin.spring.di.config.InjectByType;

public class LawDocumentChecker implements DocumentChecker {
    @InjectByType
    private Recommendator recommendator;
    @Override
    public void checkDocumentAvailability() {
        System.out.println("Документы проверены на законность!");
        recommendator.recommend();
    }
}
