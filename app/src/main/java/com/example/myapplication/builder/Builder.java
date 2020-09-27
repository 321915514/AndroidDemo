package com.example.myapplication.builder;

public abstract class Builder {
    public abstract void  buildPartA();
    public abstract void buildPartB();
    public abstract void buildPartC();
    Product product = new Product();
        public Product getResult(){
            return product;
        }
}
