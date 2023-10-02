package com.design.action.visitor;

public interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}