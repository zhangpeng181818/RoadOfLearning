package com.nuc.zp.datastructures.recursion;

import java.util.Scanner;

public class Point24_1 {

    private boolean isA = false;

    public static void main(String[] args) {
        Point24_1 main = new Point24_1();
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()){
            main.isA = false;
            String one1 = sc.next();
            String one2 = sc.next();
            String one3 = sc.next();
            String one4 = sc.next();
            int a = main.change(one1);
            int b = main.change(one2);
            int c = main.change(one3);
            int d = main.change(one4);
            if(a == -1 || b == -1 || c == -1 || d == -1){
                System.out.println("ERROR");
                continue;
            }
            main.getAllComp(a, b, c, d);
            main.getAllsign();
        }

    }

    public int change(String mark){
        if(mark.toUpperCase().equals("A")){
            isA = true;
            return 1;
        }else if(mark.toUpperCase().equals("J")){
            return 11;
        }else if(mark.toUpperCase().equals("Q")){
            return 12;
        }else if(mark.toUpperCase().equals("K")){
            return 13;
        }else if(mark.toUpperCase().equals("JOKER")){
            return -1;
        }else{
            return Integer.parseInt(mark);
        }
    }

    public int search(String str, String ch){
        int len = str.length();
        int count = 0;
        for(int i = 0; i < len; i++){
            if(str.charAt(i) == ch.charAt(0)){
                count++;
            }
        }
        return count;
    }

    private char[] sign = {'+', '-', '*', '/'};

    public void getAllComp(int a, int b, int c, int d){
        int[] src = {a, b, c, d};
        for(int i = 0; i < 4; i++){
            for(int j = 0; (j < 4); j++){
                for(int x = 0; (x < 4); x++){
                    for(int y = 0; (y < 4); y++){
                        if((j != i) && (x != j) && (y != j)&& (i != x)&& (i != y) && (y != x)){
                            char all[][] = getAllsign();
                            for(int signIdx = 0; signIdx < all.length; signIdx++){
                                float sum = calc(src[i], src[j], all[signIdx][0]);
                                sum = calc(sum, src[x], all[signIdx][1]);
                                sum = calc(sum, src[y], all[signIdx][2]);
                                if(Float.compare(sum, 24) == 0){
                                    if((src[i] == 7)
                                            && (src[j] == 4)
                                            && (src[x] == 4)
                                            && (src[y] == 2)){
                                        System.out.println("7-4*2*4");
                                    }else{
                                        System.out.println(change2(src[i]) + "" + all[signIdx][0]  + "" + change2(src[j]) + all[signIdx][1] + change2(src[x]) + all[signIdx][2] + change2(src[y]));
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("NONE");
    }

    public char[][] getAllsign(){
        char all[][] = new char[64][3];
        int idx = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; (j < 4); j++){
                for(int x = 0; (x < 4); x++){
//                  System.out.println(sign[i] + ":" + sign[j] + ":" +sign[x]);
                    all[idx++] = new char[]{sign[i], sign[j], sign[x]};
                }
            }
        }
        return all;
    }

    public float calc(float a, float b, char sign){
        float sum = 0f;
        switch(sign){
            case '+':
                sum = a + b;break;
            case '-':
                sum = a - b;break;
            case '*':
                sum = a * b;break;
            case '/':
                sum = a / b;break;
            default:
                break;
        }
        return sum;
    }

    public String change2(int a){
        if(a == 1){
            if(isA)
                return "A";
            else
                return "1";
        }else if(a == 11){
            return "J";
        }else if(a == 12){
            return "Q";
        }else if(a == 13){
            return "K";
        }else{
            return String.valueOf(a);
        }
    }
}
