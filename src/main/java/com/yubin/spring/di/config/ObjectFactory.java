package com.yubin.spring.di.config;

import com.yubin.spring.di.businesslogic.DocumentChecker;
import com.yubin.spring.di.businesslogic.LawDocumentChecker;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();
    private Config config;
    private List<ObjectConfigurator> configurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory() {
        this.config = new JavaConfig("com.yubin.spring.di",
                new HashMap<>(Map.of(DocumentChecker.class, LawDocumentChecker.class)));
        for (Class<? extends ObjectConfigurator> aClass : config.getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
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

        //Настраиваем наш объект
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t));

        return t;
    }
}
