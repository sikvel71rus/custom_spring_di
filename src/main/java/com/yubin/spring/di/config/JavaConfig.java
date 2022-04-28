package com.yubin.spring.di.config;

import lombok.Getter;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;
@Getter
public class JavaConfig implements Config {
    private Reflections scanner;
    private Map<Class,Class> ifcToImplClassMap;

    public JavaConfig(String packageToScan, Map<Class, Class> ifcToImplClassMap) {
        this.scanner = new Reflections(packageToScan);
        this.ifcToImplClassMap = ifcToImplClassMap;
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        //Берем из мапы, чтобы уменьшить связанность, легко дописываем новые конфигураторы
         return ifcToImplClassMap.computeIfAbsent(ifc,aClass-> {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
            if(classes.size()!=1){
                throw new RuntimeException(ifc + " has 0 ore more than 1 impl, please update your config map");
            }
            //Берем первый объект из множества set
             return classes.iterator().next();
        });


    }
}
