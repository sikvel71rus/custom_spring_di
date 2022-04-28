package com.yubin.spring.di.config;

public interface ProxyConfigurator {
    Object replaceWithProxy(Object t, Class implClass);
}
