package com.company.leetcode.thread.ab;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FooBarQueue {
    private int n;

    //private Semaphore foo=new Semaphore(0);
    //private Semaphore bar=new Semaphore(1);
    private BlockingQueue<Integer> b1=  new ArrayBlockingQueue(1);

    private BlockingQueue<Integer> b2=new ArrayBlockingQueue<Integer>(1);


    public FooBarQueue(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            //bar.acquire();
            b1.put(1);
            printFoo.run();
            //foo.release();
            b2.put(1);
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            //foo.acquire();
            b1.take();
            printBar.run();
            //bar.release();
            b2.take();
        }

    }

    public static void main(String[] args) {
        FooBarQueue fooBar=new FooBarQueue(1);

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


    }
}
