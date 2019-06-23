package com.nuc.zp.datastructures.stack;

/**
 * 思路：
 * （1）通过一个index值(索引)，来遍历表达式
 * （2）如果发现是一个数字，直接入栈
 * （3）如果发现是一个符号，则分如下情况：
 * 3-1）如果发现当前符号栈为空，则直接入栈
 * 3-2）如果符号栈有操作符，就进行比较；如果当前的操作符的优先级小于或等于栈中的操作符，这时
 * 就需要从数栈中pop出两个数，符号栈中pop出一个符号，然后进行计算，将得到的结果入数栈，
 * 最后将当前符号入符号栈。反之，直接入符号栈。
 * （4）当表达式扫描完毕，就顺序从数栈和符号栈pop出对应的数和符号，并运算
 * （5）最后在数栈中只有一个数字，就是表达式的结果。
 * <p>
 * 7  4
 * -
 */
public class CalculatorDemo {
    public static void main(String[] args) {
        String expression = "5-2*3+1";
        //创建两个栈，一个数栈，一个符号栈
        ArraysStack numStack = new ArraysStack(15);
        ArraysStack operStack = new ArraysStack(15);

        //通过一个index值(索引)，来遍历表达式
        int index = 0;

        int num1, num2, oper, res = 0;

        char ch = ' ';

        String keepNum = "";

        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                } else {

                    if (operStack.priority(ch) > operStack.priority(operStack.peek())) {
                        operStack.push(ch);
                    } else {

                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        numStack.push(numStack.cal(num1, num2, oper));

                        if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            oper = operStack.pop();
                            numStack.push(numStack.cal(num1, num2, oper));
                        }
                        operStack.push(ch);


                    }
                }
            } else {

                keepNum += ch;

                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {

                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            numStack.push(numStack.cal(num1, num2, oper));
        }

        System.out.printf("%s=%d\n", expression, numStack.pop());
    }

}

/**
 * 定义一个ArrayStack表示栈
 */
class ArraysStack {
    /**
     * 栈的大小
     */
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArraysStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满！");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空！");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空！");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return stack[top];
    }

    //返回运算符的优先级
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}


