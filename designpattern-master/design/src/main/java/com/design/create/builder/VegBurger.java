package com.design.create.builder;

public class VegBurger extends Burger {

    @Override
    public float price() {
        return 25.0f;
    }

    @Override
    public int age() {
        return 0;
    }

    @Override
    public String name() {
        return "Veg Burger";
    }
}