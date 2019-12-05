package com.company.leetcode.thread.ab;

import java.util.concurrent.Semaphore;

public class FooBar {
    private int n;

    private Semaphore foo=new Semaphore(0);
    private Semaphore bar=new Semaphore(1);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            bar.acquire();
            printFoo.run();
            foo.release();
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            foo.acquire();
            printBar.run();
            bar.release();
        }

    }

    public static void main(String[] args) {
        FooBar fooBar=new FooBar(1);

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
