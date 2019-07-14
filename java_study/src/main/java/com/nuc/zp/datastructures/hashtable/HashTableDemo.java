package com.nuc.zp.datastructures.hashtable;

import java.util.Scanner;

public class HashTableDemo {

    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:按雇员id查找");
            System.out.println("exit:退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id:");
                    int id = scanner.nextInt();
                    System.out.println("输入姓名：");
                    String name = scanner.next();
                    hashTab.add(new Emp(id, name));
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的ID:");
                    Emp emp = hashTab.findById(scanner.nextInt());
                    if (emp == null) {
                        System.out.println("找不到");
                    } else {
                        System.out.println("id=>" + emp.id + " name=>" + emp.name);
                    }
                    break;
                case "exit":
                    System.out.println("退出系统");
                    System.exit(0);
                default:
                    break;
            }

        }
    }


}

class HashTab {

    private EmpLinkedList[] empLinkedListArray;

    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        int empLinkedListNO = hashFun(emp.id);
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i + 1);
        }
    }

    public Emp findById(int id) {
        Emp emp = empLinkedListArray[id % size].findEmpById(id);
        return emp;
    }

    public int hashFun(int id) {
        return id % size;
    }

}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList {

    private Emp head = null;

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
        } else {
            Emp curEmp = head;
            while (curEmp.next != null) {
                head = curEmp.next;
            }
            curEmp.next = emp;
        }
    }

    public void list(int no) {
        if (head == null) {
            System.out.println("第" + no + "链表为空");
            return;
        }
        Emp curEmp = head;

        while (true) {
            System.out.println("id => " + curEmp.id + "  name => " + curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
    }

    public Emp findEmpById(int id) {
        if (head == null) {
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}
