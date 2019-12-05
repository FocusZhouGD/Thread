package com.company.base.object;


public abstract class Operation {

    private double numA=0;
    private double numB=0;

    public double getNumA() {
        return numA;
    }

    public double getNumB() {
        return numB;
    }

    public void setNumA(double numA) {
        this.numA = numA;
    }

    public void setNumB(double numB) {
        this.numB = numB;
    }

    //
    public abstract double getResult(double numA, double numB);
}
