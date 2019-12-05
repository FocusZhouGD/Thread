package com.company.leetcode;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class FooCycliBarry {

    /**
     * CyclicBarrier 原则上是使用在循环上。可以通过不同的场景理解不同的用法
     *
     */
    CyclicBarrier c1=new CyclicBarrier(1);
    CyclicBarrier c2=new CyclicBarrier(1);



    public FooCycliBarry() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        try {
            c1.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    public void second(Runnable printSecond) throws InterruptedException {
        // printSecond.run() outputs "second". Do not change or remove this line.

        try {
            c1.await();
            printSecond.run();
            c2.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        try {
            c2.await();
            printThird.run();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
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

        FooCycliBarry foo=new FooCycliBarry();

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
