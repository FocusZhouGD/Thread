package com.company.leetcode;

public class FooNOLock {
    /**
     * volatile  线程可见性
     */


    volatile int condition = 1;


    public FooNOLock() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        printFirst.run();
        condition = 2;


    }

    public void second(Runnable printSecond) throws InterruptedException {
       while(condition!=2);
        printSecond.run();
        condition=3;

    }

    public void third(Runnable printThird) throws InterruptedException {
        while(condition!=3);
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

        FooNOLock foo = new FooNOLock();

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
