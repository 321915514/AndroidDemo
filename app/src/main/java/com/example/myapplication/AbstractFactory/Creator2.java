package com.example.myapplication.AbstractFactory;

public class Creator2 extends AbstractCreator {

    @Override
    public AbstractProductA createProductA() {
        return new Product2();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}
