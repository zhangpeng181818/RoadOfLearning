package com.nuc.zp.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图的领结矩阵
    private int numOfEdges;//表示边的数目

    private boolean[] isVisited;

    public static void main(String[] args) {
        example2();
    }

    public static void example1() {
        int n = 5;//节点的个数
        String vertexs[] = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        //显示领结矩阵
        graph.showGraph();

//        graph.dfs();
        graph.bfs();
    }

    public static void example2() {
        int n = 8;//节点的个数
        String vertexs[] = {"1", "2", "3", "4", "5","6","7","8"};
        Graph graph = new Graph(n);
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        //显示领结矩阵
        graph.showGraph();

        graph.dfs();
//        graph.bfs();
    }

    public Graph(int numOfVertex) {
        vertexList = new ArrayList<>(numOfVertex);
        edges = new int[numOfVertex][numOfVertex];
        isVisited = new boolean[numOfVertex];
    }

    //得到第一个领结节点的下标w
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < edges.length; j++) {
            if (edges[index][j] != 0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个领结节点的下标来获取下一个领结节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < edges.length; j++) {
            if (edges[v1][j] != 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先
    public void dfs(boolean[] isVisited, int i) {
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs重载
    public void dfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    public void bfs(boolean[] isVisited, int i) {
        //u 表示队列的头结点对应的下标
        //w 邻接节点w
        int u, w;
        LinkedList<Integer> queue = new LinkedList<>();

        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()) {
            u = queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }

        }

    }

    public void bfs() {

        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    public void insertVertex(String vertext) {
        vertexList.add(vertext);
    }

    public void insertEdge(int v1, int v2, int value) {
        edges[v1][v2] = value;
        numOfEdges++;
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public String getValueByIndex(int index) {
        if (index < 0 || index > vertexList.size()) {
            return null;
        }
        return vertexList.get(index);
    }

    public int getWeight(int i, int j) {
        return edges[i][j];
    }

    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }
}
