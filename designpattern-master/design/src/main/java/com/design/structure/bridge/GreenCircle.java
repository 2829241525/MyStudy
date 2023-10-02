package com.design.structure.bridge;

public class GreenCircle implements DrawAPI {


    @Override
    public void drawCircle(int radius, int x, int y, Enum color) {
        System.out.println("Drawing Circle[ color: " + color + ", radius: " + radius + ", x: " + x + ", y: " + y + "]");

    }
}