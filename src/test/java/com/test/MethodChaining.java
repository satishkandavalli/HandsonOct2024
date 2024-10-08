package com.test;

public class MethodChaining {
    public static void main(String[] args) {
        a().b().c();
    }

    public static MethodChaining a()
    {
        System.out.println("This is method A");
        return new MethodChaining();
    }
    public MethodChaining b()
    {
        System.out.println("This is method B");
        return this;
    }
    public MethodChaining c()
    {
        System.out.println("This is method C");
        return this;
    }

}
