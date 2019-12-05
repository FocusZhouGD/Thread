package com.company.leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FooLock {
    /**
     * 可重入锁，提供了公平锁和非公平锁两种实现
     */


    Lock lock = new ReentrantLock();

    volatile int condition = 1;
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public FooLock() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        lock.lock();
        try {
            printFirst.run();
            condition = 2;
            //condition2.signal();
            //可以试试 signalAll();
            condition2.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        try {
            while (condition != 2) {
                condition2.await();
            }
            printSecond.run();
            condition = 3;
            //condition3.signal();
            condition3.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void third(Runnable printThird) throws InterruptedException {

        lock.lock();
        try {
            while (condition != 3) {
                condition3.await();
            }
            printThird.run();

        } finally {
            lock.unlock();
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

        FooLock foo = new FooLock();

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
