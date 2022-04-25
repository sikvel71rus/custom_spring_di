package com.yubin.spring.di;

import com.yubin.spring.di.businesslogic.Company;
import com.yubin.spring.di.businesslogic.DocumentAuditor;

public class Application {
    public static void main(String[] args) {
        DocumentAuditor documentAuditor = new DocumentAuditor();
        Company company = new Company("Рога и копыта");
        documentAuditor.start(company);
    }
}
