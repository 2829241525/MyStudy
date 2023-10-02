package com.design.create.builder;

public class ChickenBurger extends Burger {

    @Override
    public String name() {

        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public int age() {
        return 18;
    }


}