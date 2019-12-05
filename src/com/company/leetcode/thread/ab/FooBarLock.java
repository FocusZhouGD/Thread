package com.company.leetcode.thread.ab;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FooBarLock {
    private int n;

   // private Semaphore foo=new Semaphore(0);
    //private Semaphore bar=new Semaphore(1);
    private volatile  boolean flag=false;
    private Lock lock =new ReentrantLock();
    Condition condition=lock.newCondition();


    public FooBarLock(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            //bar.acquire();
            lock.lock();
            if(flag){
                condition.await();
            }
            printFoo.run();
            flag=true;
            //foo.release();
            condition.signal();
            lock.unlock();
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            //foo.acquire();
            lock.lock();
            while(!flag){
                condition.await();
            }
            printBar.run();
            flag=false;
            condition.signal();
            lock.unlock();
            //bar.release();
        }

    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        FooBarLock fooBar=new FooBarLock(1);
        Runnable ss=new Runnable() {
            @Override
            public void run() {
                System.out.println("foo");
            }
        };
        Runnable a =()->{
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
        System.out.println(System.currentTimeMillis()-start+":ms");

    }
}
