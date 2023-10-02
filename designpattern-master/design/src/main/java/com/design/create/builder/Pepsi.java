package com.design.create.builder;

public class Pepsi extends ColdDrink {

    @Override
    public float price() {
        return 35.0f;
    }

    @Override
    public int age() {
        return 0;
    }

    @Override
    public String name() {
        return "Pepsi";
    }
}