package com.nuc.zp.datastructures;

/**
 * auther: ZP
 * time:   2019/6/8 14:51
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode heroNode5 = new HeroNode(2, "李逵", "黑旋风");

        SingleLinkedList linkedList = new SingleLinkedList();
        try {
            linkedList.addByOrder(heroNode1);
            linkedList.addByOrder(heroNode3);
            linkedList.addByOrder(heroNode4);
            linkedList.addByOrder(heroNode2);
            linkedList.showNodes();
            System.out.println("------更新------");
//            linkedList.updateNode(heroNode5);
//            linkedList.showNodes();
            System.out.println("------删除------");
//            System.out.println("被删除节点：" + linkedList.deletenNodeByNo(1));
            linkedList.showNodes();
            System.out.println("------节点个数------");
            System.out.println(linkedList.size(linkedList.getHead()));
            System.out.println("------倒数第1个------");
//            System.out.println(linkedList.getLastIndexNode(4));
            System.out.println("------链表反转------");
            linkedList.reverseNodes();
            linkedList.showNodes();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    public void addNode(HeroNode node) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    public void addByOrder(HeroNode node) throws Exception {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > node.no) {
                break;
            } else if (temp.next.no == node.no) {
                throw new RuntimeException("编号存在！");
            }
            temp = temp.next;
        }
        node.next = temp.next;
        temp.next = node;
    }


    public void updateNode(HeroNode node) {
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                System.out.println("链表为空！");
                break;
            }
            if (temp.no == node.no) {
                temp.name = node.name;
                temp.nickName = node.nickName;
                break;
            }
            temp = temp.next;

        }
    }

    public HeroNode deletenNodeByNo(int no) {
        HeroNode value = null;
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                value = temp.next;
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
        return value;
    }

    /**
     * 链表反转
     */
    public void reverseNodes() {
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode newHead = new HeroNode(0, "", "");
        while (true) {
            if (head.next == null) {
                break;
            }
            HeroNode temp = head.next;
            head.next = head.next.next;
            temp.next = newHead.next;
            newHead.next = temp;
        }
        head.next = newHead.next;
    }

    /**
     * 返回倒数某个节点
     */
    public HeroNode getLastIndexNode(int index) {
        HeroNode temp = head.next;

        if (index <= 0 || index > size(head) || temp == null) {
            return null;
        }

        for (int i = 0; i < (size(head) - index); i++) {
            temp = temp.next;
        }
        return temp;
    }

    public boolean isEmpty() {
        return head.next == null;
    }

    public int size(HeroNode node) {
        if (node.next == null) {
            return 0;
        }
        int length = 0;

        HeroNode temp = node.next;
        while (temp != null) {
            temp = temp.next;
            length++;
        }
        return length;
    }

    public void showNodes() {
        if (isEmpty()) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

//定义节点
class HeroNode {

    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int hNo, String hName, String hNickNmae) {
        this.no = hNo;
        this.name = hName;
        this.nickName = hName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName +
                '}';
    }
}
