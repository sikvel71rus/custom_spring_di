package com.yubin.spring.di.config;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    private final ApplicationContext context;

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        for (Class<? extends ObjectConfigurator> aClass :context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
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
    public <T> T createObject(Class<T> implClass ){

        //Создаем новый инстанс объекта
        T t = implClass.getDeclaredConstructor().newInstance();

        //Настраиваем наш объект
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));

        return t;
    }
}
