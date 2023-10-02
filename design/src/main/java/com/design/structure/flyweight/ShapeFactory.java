package com.design.structure.flyweight;

import java.util.HashMap;

public class ShapeFactory {
    private static final HashMap<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle)circleMap.get(color);

        if(circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }

    public void set(Circle circle) {
        circle.setX(10);
        circle.setY(10);
    }
}