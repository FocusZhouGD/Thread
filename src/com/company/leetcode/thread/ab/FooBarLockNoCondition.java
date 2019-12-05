package com.company.leetcode.thread.ab;

public class FooBarLockNoCondition {
    private int n;

    // private Semaphore foo=new Semaphore(0);
    //private Semaphore bar=new Semaphore(1);
    private volatile boolean flag;
    private Object lock;


    public FooBarLockNoCondition(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            //lock.lock();
            synchronized (lock) {
                if (flag) {
                    lock.wait();
                }
                printFoo.run();
                flag = true;
                lock.notify();
                //condition.signal();
                //lock.unlock();
            }
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            //lock.lock();
            synchronized (lock) {
                while (!flag) {
                    lock.wait();
                }
                printBar.run();
                flag = false;
                lock.notify();
                //condition.signal();
                //lock.unlock();
            }
        }

    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        FooBarLockNoCondition fooBar = new FooBarLockNoCondition(1);
        Runnable ss = new Runnable() {
            @Override
            public void run() {
                System.out.println("foo");
            }
        };
        Runnable a = () -> {
            System.out.println("bar");
        };

        try {
            fooBar.foo(ss);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            fooBar.bar(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start + ":ms");

    }
}
