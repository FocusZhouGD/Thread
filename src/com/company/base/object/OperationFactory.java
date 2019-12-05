package com.company.base.object;

public class OperationFactory {

    public static Operation getOperation(String opr){
        switch (opr){
            case "+":
                return new OperationAdd();
            case "/":
                return new OperationDivide();
            default:
                return null;


        }

    }
}
