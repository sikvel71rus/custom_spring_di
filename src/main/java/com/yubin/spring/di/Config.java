package com.yubin.spring.di;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> ifc);
}
