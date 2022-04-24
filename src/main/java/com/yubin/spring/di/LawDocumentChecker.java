package com.yubin.spring.di;

public class LawDocumentChecker implements DocumentChecker {
    @Override
    public void checkDocumentAvailability() {
        System.out.println("Документы проверены на законность!");
    }
}
