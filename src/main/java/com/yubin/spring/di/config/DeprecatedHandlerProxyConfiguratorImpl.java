package com.yubin.spring.di.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DeprecatedHandlerProxyConfiguratorImpl implements ProxyConfigurator {
    @Override
    public Object replaceWithProxy(Object t, Class implClass) {
        if (implClass.isAnnotationPresent(Deprecated.class)) {
            return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("Вы используете УСТАРЕВШИЙ КЛАСС");
                    return method.invoke(t);
                }
            });
        } else {
            return t;
        }
    }
}
