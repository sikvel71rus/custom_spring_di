package com.yubin.spring.di.businesslogic;

import com.yubin.spring.di.config.InjectProperty;

public class RecommendatorImpl implements Recommendator {

    @InjectProperty("anotherService")
    private String productService;

    @Override
    public void recommend() {
        System.out.println(String.format("Так же воспользуйтесь прочими услугами, например %s", productService));

    }
}
