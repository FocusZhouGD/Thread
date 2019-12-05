package com.company.base.decorate;

public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }


    public void WearTShirts(){
        System.out.println("T滈");
    }
    public void WearBigTrouser(){
        System.out.println("大胡子");
    }
    public void WearSuit(){
        System.out.println("穿西装");
    }


}
