package com.yubin.spring.di.config;

import java.util.Map;

public class SpringApplication {
    public static ApplicationContext run(String packageToScan, Map<Class,Class> ifcToImplClass){
        Config config = new JavaConfig(packageToScan, ifcToImplClass);
        ApplicationContext applicationContext = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(applicationContext);
        applicationContext.setFactory(objectFactory);

        return applicationContext;
    }

}
