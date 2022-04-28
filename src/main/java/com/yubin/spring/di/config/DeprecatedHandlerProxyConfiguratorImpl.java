package com.yubin.spring.di.config;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DeprecatedHandlerProxyConfiguratorImpl implements ProxyConfigurator {
    @Override
    public Object replaceWithProxy(Object t, Class implClass) {

        if (implClass.getInterfaces().length==0){
            return Enhancer.create(implClass, (InvocationHandler) (o, method, args) -> {
                return getInvacationsHandlerLogic(t, method, args);
            });
        }

        if (implClass.isAnnotationPresent(Deprecated.class)) {
            return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), (proxy, method, args) -> {
                return getInvacationsHandlerLogic(t, method, args);
            });
        } else {
            return t;
        }
    }

    private Object getInvacationsHandlerLogic(Object t, Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        System.out.println("Вы используете УСТАРЕВШИЙ КЛАСС");
        return method.invoke(t, args);
    }
}
