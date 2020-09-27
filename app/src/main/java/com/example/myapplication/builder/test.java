package com.example.myapplication.builder;

public class test {
    public static void main(String[] args) {
        Builder builder = new ContreteBuilder();
        Product product = builder.getResult();
    }
}
