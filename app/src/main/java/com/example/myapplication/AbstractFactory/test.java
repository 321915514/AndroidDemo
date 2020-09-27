package com.example.myapplication.AbstractFactory;

public class test {
    /**
     * 抽象工厂模式，定义产品的抽象类以及实现类，然后定义抽象生产类，并且定义产品族，然后写出其实现类。
     * public abstract class AbstractCreator {
     *     public abstract AbstractProductA createProductA();
     *     public abstract AbstractProductB createProductB();
     * }
     *
     * // 抽象生产的实现类
     *     @Override
     *     public AbstractProductA createProductA() {
     *         return new Product1();
     *     }
     *
     *     @Override
     *     public AbstractProductB createProductB() {
     *         return new ProductB1();
     *     }
     *     // 应用
     *         AbstractCreator abstractCreator1 = new Creator2();
     *         AbstractProductB b = abstractCreator.createProductB();
     * @param args
     */
    public static void main(String[] args) {
        AbstractCreator abstractCreator = new Creator1();
        AbstractCreator abstractCreator1 = new Creator2();
        AbstractProductB b = abstractCreator.createProductB();
        AbstractProductA a = abstractCreator.createProductA();
        AbstractProductA a1 = abstractCreator1.createProductA();
        AbstractProductB b1 = abstractCreator1.createProductB();
    }
}
