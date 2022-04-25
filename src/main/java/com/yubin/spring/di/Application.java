package com.yubin.spring.di;

import com.yubin.spring.di.businesslogic.Company;
import com.yubin.spring.di.businesslogic.DocumentAuditor;
import com.yubin.spring.di.config.ObjectFactory;

public class Application {
    public static void main(String[] args) {
        DocumentAuditor documentAuditor = ObjectFactory.getInstance().createObject(DocumentAuditor.class);
        Company company = new Company("Рога и копыта");
        documentAuditor.start(company);
    }
}
