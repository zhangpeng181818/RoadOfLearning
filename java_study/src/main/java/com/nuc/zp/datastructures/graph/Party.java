package com.nuc.zp.datastructures.graph;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 整个公司的人员结构可以看作是一棵标准的多叉树。树的头节点是公司唯一的老板，除老板外，
 * 每个员工都有唯一的直接上级，叶节点是没有任何下属的基层员工，除基层员工外，每个员工都有一个或多个直接下级，另外每个员工都有一个快乐值。
 * 这个公司现在要办 party，你可以决定哪些员工来，哪些员工不来。但是要遵循如下的原则：
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来。
 * 2.派对的整体快乐值是所有到场员工快乐值的累加。
 * 3.你的目标是让派对的整体快乐值尽量大。
 * 给定一棵多叉树，请输出派对的最大快乐值。
 */
public class Party {

    /**
     * 整个公司的人员结构可以看作是一棵标准的多叉树。树的头节点是公司唯一的老板，除老板外，每个员工都有唯一的直接上级，叶节点是没有任何下属的基层员工，
     * 除基层员工外，每个员工都有一个或多个直接下级，另外每个员工都有一个快乐值。 这个公司现在要办
     * party，你可以决定哪些员工来，哪些员工不来。但是要遵循如下的原则：
     * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来。
     * 2.派对的整体快乐值是所有到场员工快乐值的累加。
     * 3.你的目标是让派对的整体快乐值尽量大。 给定一棵多叉树，请输出派对的最大快乐值。
     *  input
     *  3 1
     *  5 1 1
     *  1 2
     *  1 3
     *
     *  output
     *  5
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //第一行，输入公司总人数n，公司的老板root
        int n = sc.nextInt();
        int root = sc.nextInt();
        //第二行，输入n个整数happy_i表示员工i的快乐值
        int[] happy = new int[n+1];
        for(int i=1;i<n+1;i++){
            happy[i] = sc.nextInt();
        }
        //接下来n-1行的每行输入两个整数 u_i 和 v_i 表示 u_i 是 v_i 的直接上级
        Map<Integer, List<Integer>> nodes=new LinkedHashMap<Integer, List<Integer>>();//存储每个结点

        for(int i=0;i<n-1;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            List<Integer> ll = new ArrayList<>();
            //如果整棵树中包含a这个结点，就添加
            if(nodes.containsKey(a)){
                ll = nodes.get(a);
            }
            ll.add(b);
            nodes.put(a, ll);

        }


        System.out.println(solve(happy, nodes, root));

    }

    private static int solve(int[] happy, Map<Integer, List<Integer>> map, int root) {
        RType ans = dfs(happy, map, root);
        return Math.max(ans.with, ans.without);
    }

    private static RType dfs(int[] happy, Map<Integer,List<Integer>> map, int root) {
        RType ans = new RType(happy[root], 0);
        if (map.containsKey(root)) {
            for (int sub : map.get(root)) {
                RType cur = dfs(happy, map, sub);
                ans.with += cur.without;
                ans.without += Math.max(cur.with, cur.without);
            }
        }
        return ans;
    }

    static class RType {
        int with;
        int without;

        RType(int with, int without) {
            this.with = with;
            this.without = without;
        }
    }

}
