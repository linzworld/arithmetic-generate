package com.lzh;

/**
 * @Description 一个存放运算符的节点
 * @Author 林泽鸿
 * @Date 2020/4/9 21:22
 */
public class OperatorNode extends Node {

    //运算符
    public String operator;

    public OperatorNode(Node left, Node right, String operator) {
        //父类中无用的常量设置为null
        super(null, left, right, 0);
        this.operator = operator;
    }
}
