package com.nuc.zp.datastructures;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addBoy(25);
//        list.showBoy();
        list.conutBoy(13,2,25);
    }
}

class CircleSingleLinkedList {
    //创建first节点，当前没有编号
    private Boy first = null;

    /**
     * 添加小孩节点，构建环形链表
     * 思路：
     * （1）先创建第一个节点，让first指向该节点，并形成环形
     * （2）后面当我们每创建一个新的节点，就把该节点，加入到已有的环形链表中
     *
     * @param nums
     */
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                curBoy = boy;
                curBoy.setNext(first);
            }
        }
    }


    /**
     * 遍历当前环形链表
     * 思路：
     * （1）先让一个辅助指针curBoy，指向first节点
     * （2）然后通过while循环遍历该环形链表即可，直到curBoy.next==first为止
     */
    public void showBoy() {
        if (first == null) {
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.println("小孩的编号：" + curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 思路：
     *     （1）创建一个辅助指针helper，事先指向环形链表的最后一个节点
     *     （2）当小孩报数时，让first和helper同时移动m-1次
     *     （3）这时first指向的小孩开始出圈，
     *          first = first.next;
     *          helper.next = first;
     *          原来first指向的节点就没有任何引用，就会被回收。
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少个小孩在圈中
     */
    public void conutBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误！");
            return;
        }

        //创建辅助指针
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }

        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        while (true) {
            if (helper == first) {//说明圈中只有1人
                break;
            }
            //让first helper 指针同时移动countNum-1次
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩%d出圈\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号:%d\n",first.getNo());
    }
}

//创建Boy类，表示节点
class Boy {

    private int no;//编号
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
