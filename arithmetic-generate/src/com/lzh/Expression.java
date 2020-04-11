package com.lzh;


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

    //出现n除以0的情况
    private boolean isDivideForZero = false;

    public boolean isDivideForZero() {
        return isDivideForZero;
    }

    public void setDivideForZero(boolean divideForZero) {
        isDivideForZero = divideForZero;
    }

    //生成答案的范围
    public static int range;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    //生成表达式
    public Expression(int operator_number, int answer_range) {
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
            root = generateNode(GenerateUtils.getRandomInRange(operator_number) + 1);
        }
    }


    /**
     * 构建生成四则运算表达式的二叉树
     * @Param number 运算符的个数
     * @Return com.lzh.Node 二叉树的头节点
     * @Author 林泽鸿
     * @Date 2020/4/11 17:41
     **/
    public Node generateNode(int number) {
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
        Fraction result = calculate(parent.operator, parent.left.result, parent.right.result);
        //如果是负数,就是出现小的减去大的情况，这时候交换左右孩子
        if (result.isNegative()) {
            Node tmp = parent.left;
            parent.left = parent.right;
            parent.right = tmp;
        }
        parent.result = result;
        //计算树高
        parent.high = Math.max(parent.left.high, parent.right.high) + 1;
        return parent;
    }


    //进行两个元素的计算
    private Fraction calculate(String operator, Fraction leftFraction, Fraction rightFraction) {
        switch (operator) {
            case ADD:
                return leftFraction.add(rightFraction);
            case SUBTRACT:
                return leftFraction.subtract(rightFraction);
            case MULTIPLY:
                return leftFraction.multiply(rightFraction);
            //可能会出现除以0的情况，即rightFraction可能为0
            case DIVIDE:
                if (rightFraction.getA() == 0) {
                    this.isDivideForZero = true;
                    rightFraction.setA(1);
                }
                return leftFraction.divide(rightFraction);
            default:
                throw new RuntimeException("该操作符不存在");
        }
    }

    //打印出中缀表达式，包括括号
    @Override
    public String toString() {
        return print(root);
    }

    /**
     * 中序遍历二叉树,左中右
     *
     * @Param localRootNode 当前所在的最高节点，可以不是根节点
     * @Return java.lang.String
     * @Author 林泽鸿
     * @Date 2020/4/11 17:58
     **/
    private String print(Node localRootNode) {

        if (localRootNode == null) {
            return "";
        }
        String left = print(localRootNode.left);
        String mid = localRootNode.toString();
        //需要加括号的情况,一个节点的操作符为乘除，其子节点的操作符是加减
        if (localRootNode.left instanceof OperatorNode && localRootNode instanceof OperatorNode) {
            if (leftBrackets(((OperatorNode) localRootNode.left).operator, ((OperatorNode) localRootNode).operator)) {
                left = LEFT_BRACKETS + " " + left + " " + RIGHT_BRACKETS;
            }
        }
        String right = print(localRootNode.right);
        if (localRootNode.right instanceof OperatorNode && localRootNode instanceof OperatorNode) {
            if (rightBrackets(((OperatorNode) localRootNode.right).operator, ((OperatorNode) localRootNode).operator)) {
                right = LEFT_BRACKETS + " " + right + " " + RIGHT_BRACKETS;
            }
        }
        return left + mid + right;
    }


    //向左遍历时，需要括号
    private boolean leftBrackets(String left, String mid) {
        return (isAddOrSubtract(left) && isMultiplyOrDivide(mid));
    }

    //向右遍历时，需要括号
    private boolean rightBrackets(String right, String mid) {
        //有可能出现2*3 /( 4*5 )的情况，所以不用加括号只有当
        return !(isAddOrSubtract(mid) && isMultiplyOrDivide(right));
    }

    /**
     * 是加减运算符
     *
     * @Param operator
     * @Return boolean
     * @Author 林泽鸿
     * @Date 2020/4/11 18:10
     **/
    private boolean isAddOrSubtract(String operator) {
        return operator.equals(ADD) || operator.equals(SUBTRACT);
    }

    /**
     * 是乘除运算符
     *
     * @Param operator
     * @Return boolean
     * @Author 林泽鸿
     * @Date 2020/4/11 18:10
     **/
    private boolean isMultiplyOrDivide(String operator) {
        return operator.equals(MULTIPLY) || operator.equals(DIVIDE);
    }


}

