package com.yubin.spring.di.businesslogic;

import com.yubin.spring.di.config.InjectProperty;
import com.yubin.spring.di.config.Singleton;

@Singleton
@Deprecated
public class RecommendatorImpl implements Recommendator {

    @InjectProperty("anotherService")
    private String productService;

    public RecommendatorImpl() {
        System.out.println("Рекомендатор создан");
    }

    @Override
    public void recommend() {
        System.out.printf("Так же воспользуйтесь прочими услугами, например %s%n", productService);

    }
}
