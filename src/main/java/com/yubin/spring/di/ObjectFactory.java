package com.yubin.spring.di;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();
    private Config config;

    public ObjectFactory() {
        this.config = new JavaConfig("com.yubin.spring.di",
                new HashMap<>(Map.of(DocumentChecker.class,LawDocumentChecker.class)));
    }

    public static ObjectFactory getInstance(){
        return ourInstance;
    }


    //Создаем объект (можно передать интерфейс)
    //При этом вызываемый конфиг подставит impl class
    //Работает только если у интерфейса 1 класс наследник

    /**
     *Создаем объект (можно передать интерфейс)
     *При этом вызываемый конфиг подставит impl class
     *Работает только если у интерфейса 1 класс наследник
     */
    @SneakyThrows
    public <T> T createObject(Class<T> type ){
        Class<? extends T> implClass = type;
        if (type.isInterface()){
            implClass = config.getImplClass(type);
        }
        //Создаем новый инстанс объекта
        T t = implClass.getDeclaredConstructor().newInstance();
        return t;
    }
}
