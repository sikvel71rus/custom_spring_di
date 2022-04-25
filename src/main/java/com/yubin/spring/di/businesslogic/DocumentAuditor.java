package com.yubin.spring.di.businesslogic;

import com.yubin.spring.di.config.ObjectFactory;

/**
 * Аудитор документов компании
 */
public class DocumentAuditor {

    private Announcer announcer = ObjectFactory.getInstance().createObject(Announcer.class);

    private DocumentChecker documentChecker = ObjectFactory.getInstance().createObject(DocumentChecker.class);

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
