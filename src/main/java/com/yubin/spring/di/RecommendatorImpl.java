package com.yubin.spring.di;

public class RecommendatorImpl implements Recommendator {

    @InjectProperty("anotherService")
    private String productService;

    @Override
    public void recommend() {
        System.out.println(String.format("Так же воспользуйтесь прочими услугами, например %s", productService));

    }
}
