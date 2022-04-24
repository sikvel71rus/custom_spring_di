package com.yubin.spring.di;

/**
 * Аудитор документов компании
 */
public class DocumentAuditor {

    public void start(Company company){
        //todo Предупредить компанию о необходимости получить документы
        //todo Вывести документы из компании (если ранее не получили все необходимые)

        audit(company);

        //todo Сообщить о результатах проверки

    }

    private void audit(Company company) {
        System.out.println("Происходит процесс аудита, все нервничают!");
    }
}
