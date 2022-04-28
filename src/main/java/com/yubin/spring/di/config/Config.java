package com.yubin.spring.di.config;

/**
 * Содержит информацию о том какие пакеты надо сканировать и какие имплементации у нас есть
 */
public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> ifc);

    org.reflections.Reflections getScanner();
}
