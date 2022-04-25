package com.yubin.spring.di.businesslogic;

import com.yubin.spring.di.config.InjectByType;
import com.yubin.spring.di.config.ObjectFactory;

/**
 * Аудитор документов компании
 */
public class DocumentAuditor {
    @InjectByType
    private Announcer announcer;
    @InjectByType
    private DocumentChecker documentChecker;

    public void start(Company company){
        announcer.announce("Выслан запрос на получение документов");
        documentChecker.checkDocumentAvailability();
        audit(company);
        announcer.announce("Аудит документов закончен, все хорошо!");
    }

    private void audit(Company company) {
        System.out.println(String.format("Происходит процесс аудита в компании %s!", company.name));
    }
}
