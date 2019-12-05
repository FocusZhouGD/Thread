package com.company.leetcode;

import java.util.concurrent.Semaphore;

public class Foo {

    public Semaphore semaFirstTwo = new Semaphore(-2);

    public Semaphore semaTwoSecond = new Semaphore(-2);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        semaFirstTwo.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        semaFirstTwo.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.

        printSecond.run();
        semaTwoSecond.release();
    }

    public void third(Runnable printThird) throws InterruptedException {

        semaTwoSecond.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.

        printThird.run();
    }

    public static void main(String[] args) {
        Runnable a = new Runnable() {
            @Override
            public void run() {
                System.out.println("first");
            }
        };
        Runnable b = new Runnable() {
            @Override
            public void run() {
                System.out.println("second");
            }
        };
        Runnable c = new Runnable() {
            @Override
            public void run() {
                System.out.println("third");
            }
        };
        Runnable d = () -> {
            System.out.println("four");
        };

       /**
        new Thread(c).start();
        new Thread(b).start();
        new Thread(a).start();
        */

        Foo foo=new Foo();

        /**
         * 顺序不能c b a
         * 结果： java.lang.Thread.State: WAITING (parking)
         *
         * 调整： new Semaphore(-2);
         *
         * 结果：java.lang.Thread.State: WAITING (parking)
         *
         *
         *
         *
         */



        try {
            foo.first(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            foo.second(b);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            foo.third(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




    }


}
