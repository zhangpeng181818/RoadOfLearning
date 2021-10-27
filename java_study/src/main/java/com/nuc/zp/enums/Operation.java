package com.nuc.zp.enums;

import java.util.HashMap;
import java.util.Map;

public enum Operation {

    PLUS {
        @Override
        double apply(double x, double y) {
            return x + y;
        }
    }, MINUS {
        @Override
        double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES {
        @Override
        double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE {
        @Override
        double apply(double x, double y) {
            return x / y;
        }
    };

    abstract double apply(double x, double y);


    private static final Map<String, Operation> stringToEnum = new HashMap<>();

    static {
        for (Operation operation : values()) {
            stringToEnum.put(operation.toString(),operation);
        }
    }

    public static Operation fromString(String symbol){
        return stringToEnum.get(symbol);
    }

    public static void main(String[] args) {
        System.out.println(Operation.PLUS.apply(1, 2));
        System.out.println(Operation.fromString("PLUS").apply(1,1));
    }
}
