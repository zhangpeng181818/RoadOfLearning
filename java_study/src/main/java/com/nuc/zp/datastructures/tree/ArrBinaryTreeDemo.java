package com.nuc.zp.datastructures.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0);
        System.out.println();
        arrBinaryTree.infixOrder(0);
        System.out.println();
        arrBinaryTree.postOrder(0);
    }
}

//编写一个ArrayBinaryTree，实现顺序存储二叉树遍历
class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树前序遍历。");
        }
        System.out.print(arr[index] + " ");
        if (index * 2 + 1 < arr.length) {
            preOrder(index * 2 + 1);
        }
        if (index * 2 + 2 < arr.length) {
            preOrder(index * 2 + 2);
        }
    }

    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树中序遍历。");
        }
        if (index * 2 + 1 < arr.length) {
            infixOrder(index * 2 + 1);
        }
        System.out.print(arr[index] + " ");
        if (index * 2 + 2 < arr.length) {
            infixOrder(index * 2 + 2);
        }
    }

    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树后序遍历。");
        }
        if (index * 2 + 1 < arr.length) {
            postOrder(index * 2 + 1);
        }
        if (index * 2 + 2 < arr.length) {
            postOrder(index * 2 + 2);
        }
        System.out.print(arr[index] + " ");
    }
}
