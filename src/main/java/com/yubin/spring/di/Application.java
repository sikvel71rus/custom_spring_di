package com.yubin.spring.di;

public class Application {
    public static void main(String[] args) {
        DocumentAuditor documentAuditor = new DocumentAuditor();
        Company company = new Company();
        documentAuditor.start(company);
    }
}
