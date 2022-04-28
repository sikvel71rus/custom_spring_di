package com.yubin.spring.di;

import com.yubin.spring.di.businesslogic.Company;
import com.yubin.spring.di.businesslogic.DocumentAuditor;
import com.yubin.spring.di.businesslogic.DocumentChecker;
import com.yubin.spring.di.businesslogic.LawDocumentChecker;
import com.yubin.spring.di.config.ApplicationContext;
import com.yubin.spring.di.config.SpringApplication;

import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
//        DocumentAuditor documentAuditor = ObjectFactory.getInstance().createObject(DocumentAuditor.class);
//        Company company = new Company("Рога и копыта");
//        documentAuditor.start(company);
        ApplicationContext context = SpringApplication.run("com.yubin.spring.di", new HashMap<>(Map.of(DocumentChecker.class, LawDocumentChecker.class)));
        DocumentAuditor auditor = context.getObject(DocumentAuditor.class);
        auditor.start(new Company("Рога и копыта"));
    }
}
