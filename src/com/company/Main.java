package com.company;

import com.company.base.decorate.Person;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("hello design Pattern");

        double d1,d2;
        //System.out.println(d1==d2);
        //System.out.println(d1.equel);


        Person person=new Person("小菜第一套服装");
        person.WearBigTrouser();

        Person person1=new Person("小菜的第二套服装");
        person1.WearTShirts();


    }
}
