package com.nuc.zp.datastructures.tree;

public class ThreadedBinaryTree {
    public static void main(String[] args) {
        //

        HeroNode2 root = new HeroNode2(1, "tom");
        HeroNode2 node2 = new HeroNode2(3, "jack");
        HeroNode2 node3 = new HeroNode2(6, "smith");
        HeroNode2 node4 = new HeroNode2(8, "mary");
        HeroNode2 node5 = new HeroNode2(10, "king");
        HeroNode2 node6 = new HeroNode2(14, "dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        BinaryTree2 binaryTree2 = new BinaryTree2();
        binaryTree2.setRoot(root);
        binaryTree2.threadedNodes(root);


        HeroNode2 node5Left = node5.getLeft();
        HeroNode2 node5Right = node5.getRight();
        System.out.println(node5Left);//3
        System.out.println(node5Right);//1

        System.out.println("---------");
        binaryTree2.threadedList();
    }
}

class BinaryTree2 {
    private HeroNode2 root;


    private HeroNode2 pre = null;

    public void setRoot(HeroNode2 root) {
        this.root = root;
    }

    //中序
    public void threadedNodes(HeroNode2 node) {
        if (node == null) {
            return;
        }
        //(1)线索化左子树
        threadedNodes(node.getLeft());
        //(2)处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //让当前节点的做指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型，指向前驱节点
            node.setLeftType(1);
        }
        //处理后继节点，让当前节点是下一个节点的前驱节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        //(3)线索化右子树
        threadedNodes(node.getRight());
    }

    public void threadedList() {
        HeroNode2 node = root;
        while (node != null) {
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    /**
     * 删除指定编号节点
     */
    public void deleteNode(int no) {
        if (this.root != null) {
            if (this.root.getNo() == no) {
                this.root = null;
            } else {
                this.root.delNode(no);
            }
        } else {
            System.out.println("二叉树为空，无法删除");
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public HeroNode2 perSearch(int no) {
        if (this.root != null) {
            return this.root.preSearch(no);
        } else {
            System.out.println("二叉树为空，无法查找");
            return null;
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public HeroNode2 infixSearch(int no) {
        if (this.root != null) {
            return this.root.infixSearch(no);
        } else {
            System.out.println("二叉树为空，无法遍历");
            return null;
        }
    }


    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public HeroNode2 postSearch(int no) {
        if (this.root != null) {
            return this.root.preSearch(no);
        } else {
            System.out.println("二叉树为空，无法遍历");
            return null;
        }
    }


}


class HeroNode2 {
    private int no;
    private String name;
    private HeroNode2 left;
    private HeroNode2 right;

    public HeroNode2(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //0：指向左子树  1：指向前驱节点
    //0：指向右子树  1：指向后继结点
    private int leftType;
    private int rightType;

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public HeroNode2 getLeft() {
        return left;
    }

    public HeroNode2 getRight() {
        return right;
    }

    public int getLeftType() {
        return leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeft(HeroNode2 left) {
        this.left = left;
    }

    public void setRight(HeroNode2 right) {
        this.right = right;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    /**
     * 前序遍历（中左右）
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public HeroNode2 preSearch(int no) {
        if (this.getNo() == no) {
            return this;
        }
        HeroNode2 resNode = null;
        if (this.getLeft() != null) {
            resNode = this.getLeft().preSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.getRight() != null) {
            resNode = this.getRight().preSearch(no);
        }
        return resNode;
    }


    /**
     * 中序遍历（左中右）
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public HeroNode2 infixSearch(int no) {

        HeroNode2 resNode = null;
        if (this.getLeft() != null) {
            resNode = this.getLeft().preSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        if (this.getNo() == no) {
            return this;
        }
        if (this.getRight() != null) {
            resNode = this.getRight().preSearch(no);
        }
        return resNode;
    }

    /**
     * 后序遍历(左右中)
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    public HeroNode2 postSearch(int no) {
        HeroNode2 resNode = null;
        if (this.getLeft() != null) {
            resNode = this.getLeft().preSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        if (this.getRight() != null) {
            resNode = this.getRight().preSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }
        if (this.getNo() == no) {
            return this;
        }
        return resNode;
    }

}
