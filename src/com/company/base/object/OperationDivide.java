package com.company.base.object;

public class OperationDivide extends Operation {
    @Override
    public double getResult(double numA, double numB) {
        if(Double.compare(numB,0)==0){
            throw new IllegalArgumentException("不能为0！");

        }

        return numA/numB;
    }
}
