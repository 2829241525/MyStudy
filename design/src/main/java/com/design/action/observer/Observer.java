package com.design.action.observer;

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}