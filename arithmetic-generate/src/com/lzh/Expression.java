package com.lzh;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description 四则运算表达式的生成和构建二叉树
 * @Author 林泽鸿
 * @Date 2020/4/9 17:47
 */
public class Expression {

    private static final String ADD = "＋";

    private static final String SUBTRACT = "-";

    private static final String MULTIPLY = "×";

    private static final String DIVIDE = "÷";

    private static final String LEFT_BRACKETS = "(";

    private static final String RIGHT_BRACKETS = ")";

    //运算符的种类
    private static final String[] OPERATORS = {ADD, SUBTRACT, MULTIPLY, DIVIDE};

    //根节点
    private Node root;

    //生成答案的范围
    public static int range;


    //生成表达式
    public void generateExpression(int operator_number, int answer_range) {
        if (operator_number < 1) {
            throw new RuntimeException("运算符个数必须大于0");
        }
        if (answer_range < 1) {
            throw new RuntimeException("运算结果范围必须大于等于1");
        }
        this.range = answer_range;

        if (operator_number == 1) {
            root = generateNode(operator_number);
        } else {
            root = generateNode(GenerateUtils.getRandomInRange(answer_range) + 1);
        }
    }

    /**
     * 构建生成四则运算表达式的二叉树
     *
     * @Param number 运算符的个数
     * @Return java.lang.String 中缀表达式
     * @Author 林泽鸿
     * @Date 2020/4/9 21:46
     **/
    public static Node generateNode(int number) {
        //如果是0就构造叶子节点
        if (number == 0) {
            return new Node(Fraction.generateFraction(), null, null, 1);
        }
        //其他都是构造符号节点
        OperatorNode parent = new OperatorNode(null, null, OPERATORS[GenerateUtils.getRandomInRange(4)]);
        int left = GenerateUtils.getRandomInRange(number);
        //递归下去构造左孩子和右孩子
        parent.left = generateNode(left);
        //总数要减去当前已经构建出来的这一个节点
        parent.right = generateNode(number - 1 - left);
        //然后计算结果
//        Fraction result = calculate(parent.operator, parent.left.result, parent.right.result);
        //如果是负数就取绝对值，然后交换左右孩子
//        if (result.isNegative()) {
//            Node tmp = parent.left;
//            parent.left = parent.right;
//            parent.right = tmp;
//            result.abs();
//        }
//        parent.result = result;
        //计算树高
        parent.high = Math.max(parent.left.high, parent.right.high) + 1;
        return parent;
    }


    private static Fraction calculate(String operator, String left_result, String right_result) {

        return null;
    }

}

