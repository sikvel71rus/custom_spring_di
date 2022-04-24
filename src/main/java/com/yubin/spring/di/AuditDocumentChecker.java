package com.yubin.spring.di;

public class AuditDocumentChecker implements DocumentChecker {
    @Override
    public void checkDocumentAvailability() {
        System.out.println("Проверка наличия документов. Все документы получены.");
    }
}
