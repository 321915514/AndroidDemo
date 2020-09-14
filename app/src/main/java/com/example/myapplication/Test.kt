package com.example.myapplication

import java.time.temporal.Temporal
import java.util.*


fun main() {
    print(myAdd(1,3))
    val a: Int = 10000

    val boxa: Int? =a
    val boxb: Int? =a
    print(boxa == boxb)
    print(boxa === boxb)
    for (i in 1..4){
        print(i)
    }
    for (i in 1 until 10){
        print(i)
    }
    val a1 =10
    val b =12
    var c = if(a1>b) a1 else b
    print(c)
    print("Hello world")
    print("\n")
    print("24\n")
    val person = Person("java")
    person.age =10
    print("26\n")
    val person1 = Person("c++",12)
    print("27\n")
    person1.show()
    print("\n")
    person.show()
    print("\n")
    person.inner().Test()

    var test = Test()
    test.setTest(object :transTest{
        override fun test() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            print(test.r)
        }

    })



}

class Person constructor(name: String ){
    var age: Int = 0
    var siteName = name
    init {
        print("init40\n")
    }
    constructor(name: String, age:Int) :this(name){
        //print("$name 的年龄是 $age\n")
    }
    inner class inner{
        fun Test(){
            var age = this@Person
            print("$siteName is $age")
        }
    }

    fun show(){
        //print("$siteName ------$age")
        print("$siteName 的年龄是 $age\n")
    }

}
// 抽象类
open class Base{
    open fun  f(){}
}

abstract class derived: Base(){
    override fun f() {
        super.f()
        print("Hello")
    }
}

// 匿名内部类
class Test{
    var r = "hello"

    fun setTest(test: transTest){
        test.test()
    }
}

interface transTest{
    fun test()
}




fun myAdd(a: Int,b: Int):Int{
    return a+b
}
