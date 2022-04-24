package com.yubin.spring.di;

/**
 * Аудитор документов компании
 */
public class DocumentAuditor {

    private Announcer announcer = new ConsoleAnnouncer();

    private DocumentChecker documentChecker = new AuditDocumentChecker();

    public void start(Company company){
        announcer.announce("Выслан запрос на получение документов");

        documentChecker.checkDocumentAvailability();

        audit(company);

        announcer.announce("Аудит документов закончен, все хорошо!");

    }

    private void audit(Company company) {
        System.out.println("Происходит процесс аудита, все нервничают!");
    }
}
