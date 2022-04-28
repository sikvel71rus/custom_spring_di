package com.yubin.spring.di.config;

import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
        T t = create(implClass);

        //Настраиваем наш объект
        configure(t);

        //Вызываем init метод, если он есть
        invokeInit(implClass, t);

        if (implClass.isAnnotationPresent(Deprecated.class)){
        return (T) Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Вы используете УСТАРЕВШИЙ КЛАСС");
                 return method.invoke(t);
            }
        });
        }
        return t;
    }

    private <T> void invokeInit(Class<T> implClass, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getMethods()) {
            if(method.isAnnotationPresent(PostConstruct.class)){
                method.invoke(t);
            }
        }
    }

    private <T> void configure(T t) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
    }

    private <T> T create(Class<T> implClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        T t = implClass.getDeclaredConstructor().newInstance();
        return t;
    }
}
