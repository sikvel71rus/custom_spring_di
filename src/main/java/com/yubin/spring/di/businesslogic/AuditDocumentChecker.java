package com.yubin.spring.di.businesslogic;

import com.yubin.spring.di.businesslogic.DocumentChecker;

public class AuditDocumentChecker implements DocumentChecker {
    @Override
    public void checkDocumentAvailability() {
        System.out.println("Проверка наличия документов. Все документы получены.");
    }
}
