package com.abstest;

import com.design.action.mediator.User;

public class test4
{
    static int i = 123;
    static User user;
    public static void main(String[] args) {

        user = new User("123");
        System.out.println(user);
        user = new User("456");
        System.out.println(user);
    }
}