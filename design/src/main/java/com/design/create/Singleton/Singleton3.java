package com.design.create.Singleton;

public class Singleton3 {
    private volatile static Singleton3 Singleton3;
    private Singleton3 (){}
    public static Singleton3 getSingleton() {
        if (Singleton3 == null) {
            synchronized (Singleton3.class) {
                if (Singleton3 == null) {
                    Singleton3 = new Singleton3();
                }
            }
        }
        return Singleton3;
    }
}