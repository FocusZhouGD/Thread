package com.company.leetcode.thread.hho;

import java.util.concurrent.Semaphore;

public class H2O {

    // int oCount,hCount;
    private Semaphore hSem = new Semaphore(2);
    private Semaphore oSem = new Semaphore(0);


    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hSem.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        oSem.release();
        // hCount++;

//        if(hCount>=2){
//            oCount=0;
//            oSem.release();
//
//        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oSem.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        hSem.release(2);
//        oCount++;
//        if (oCount>=1){
//            oCount=0;
//            hSem.release(2);
//        }
    }

    public static void main(String[] args) {
        H2O h2O = new H2O();
        int n = 5;
        new Thread(() -> {
            for (int i = 0; i < 2 * n; i++) {
                try {
                    h2O.hydrogen(() -> System.out.println("H"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < n; i++) {
                try {
                    h2O.oxygen(() -> System.out.println("O"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
