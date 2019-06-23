package com.nuc.zp.datastructures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {
        //逆波兰表达式
        String suffixExpression = "3 4 + 5 * 6 -";
        int res = calculate(getListString(suffixExpression));
        System.out.println("计算的结果:" + res);


        String expression = "1+((2+3)*4)-5";
        //中缀表达式
        System.out.println(toInfixExpressionList(expression));
        //后缀表达式
        System.out.println(parseSuffixExpressionList(toInfixExpressionList(expression)));
        //结果
        System.out.println(calculate(parseSuffixExpressionList(toInfixExpressionList(expression))));


    }

    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    public static int calculate(List<String> ls) {
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算有误");
                }
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.peek());
    }

    public static List<String> toInfixExpressionList(String s) {
        List<String> list = new ArrayList<>();
        int i = 0;
        String str = "";
        char c = ' ';
        do {
            if (((c = s.charAt(i)) < 48) || ((c = s.charAt(i)) > 57)) {
                list.add(c + "");
                i++;
            } else {
                str = "";
                while (i < s.length() && (((c = s.charAt(i)) >= 48) && ((c = s.charAt(i)) <= 57))) {
                    str += c;
                    i++;
                }
                list.add(str);
            }

        } while (i < s.length());
        return list;
    }

    public static List<String> parseSuffixExpressionList(List<String> list) {
        Stack<String> s1 = new Stack<>();//符号栈
        List<String> s2 = new ArrayList<>();

        for (String item : list) {
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }

        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;
    }
}

class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }
        return result;
    }
}