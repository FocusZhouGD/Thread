package com.company.leetcode.thread.zeroevenodd;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {
    private int n;
    private Semaphore zero =new Semaphore(1);
    private Semaphore even =new Semaphore(0);
    private Semaphore odd =new Semaphore(0);
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            zero.acquire();
            printNumber.accept(0);
            if(i%2==1){
                odd.release();
            }else{
                even.release();
            }


        }
    }
//偶数
    public void even(IntConsumer printNumber) throws InterruptedException {

        for (int i = 2; i <=n; i=i+2) {
            even.acquire();
            printNumber.accept(i);
           zero.release();

        }
    }
//奇数
    public void odd(IntConsumer printNumber) throws InterruptedException {

        for (int i = 1; i <= n; i=i+2) {
            odd.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd=new ZeroEvenOdd(5);



    }
}
