package com.lzh;

/**
 * @Description 定义一个存放操作数（分数）的节点
 * @Author 林泽鸿
 * @Date 2020/4/9 21:21
 */
public class Node {
    //存储当前节点以下的计算结果
    public Fraction result;
    public Node left;
    public Node right;
    public int high;

    public Node() {

    }

    public Node(Fraction result, Node left, Node right, int high) {
        this.result = result;
        this.left = left;
        this.right = right;
        this.high = high;
    }
}
