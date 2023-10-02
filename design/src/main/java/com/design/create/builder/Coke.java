package com.design.create.builder;

public class Coke extends ColdDrink {

    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 30.0f;
    }

    @Override
    public int age() {
        return 18;
    }
}