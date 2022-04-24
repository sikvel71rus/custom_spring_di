package com.yubin.spring.di;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        //настраиваем объект, смотрим есть ли филды,
        //в которые нужно добавить значение


        //Не учитываем родительские филды (для экономии времени)
        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);
                String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
                Stream<String> lines = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8)).lines();
                Map<String, String> propertiesMap = lines.map(line -> line.split("=")).collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
            if (annotation!=null){
                String value = annotation.value().isEmpty() ? propertiesMap.get(field.getName()) : propertiesMap.get(annotation.value());
                field.setAccessible(true);
                field.set(t,value);
            }
        }
        return t;
    }
}
