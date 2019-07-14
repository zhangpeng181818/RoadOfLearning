package com.nuc.zp.datastructures.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        binaryTree.setRoot(root);

        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);

//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//        System.out.println("后序遍历");
//        binaryTree.postOrder();


        System.out.println("前序查找");
        System.out.println(binaryTree.perSearch(5));

        System.out.println("中序查找");
        System.out.println(binaryTree.infixSearch(5));

        System.out.println("后序查找");
        System.out.println(binaryTree.postSearch(5));

    }

}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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

    public HeroNode perSearch(int no) {
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

    public HeroNode infixSearch(int no) {
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

    public HeroNode postSearch(int no) {
        if (this.root != null) {
            return this.root.preSearch(no);
        } else {
            System.out.println("二叉树为空，无法遍历");
            return null;
        }
    }


}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
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

    public HeroNode preSearch(int no) {
        if (this.getNo() == no) {
            return this;
        }
        HeroNode resNode = null;
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

    public HeroNode infixSearch(int no) {

        HeroNode resNode = null;
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

    public HeroNode postSearch(int no) {
        HeroNode resNode = null;
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
