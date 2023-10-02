package com.design.structure.bridge;

public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new RedCircle());
        Shape greenCircle = new Circle(100,100, 10, new GreenCircle());
        Shape greenCircle2 = new Circle(100,100, 10,Color.GREEN, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
        greenCircle2.draw();
    }
}