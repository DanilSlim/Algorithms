package com.first;

public class TestClass implements First, Second{


    public String doSomething(String message) {
        return "First";
    }

    @Override
    public String doSomethingSecond(String message) {
        return "Second";
    }
}
