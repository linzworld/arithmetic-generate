package com.lzh;

import com.cy.www.numberUtils;

/**
 * @Description 计算数（分数），统一是叶子节点
 * @Author 林泽鸿
 * @Date 2020/4/9 22:20
 */
public class Fraction {

    //分子
    private int a;
    //分母，不能为0，默认为1
    private int b = 1;
    //得到操作数
    private String result;

    public Fraction() {
    }

    //设置分子和分母
    public Fraction(int a, int b) {
        setAB(a, b);
    }

    public Fraction(String result) {
        int a_index = result.indexOf("/");
        int a1_index = result.indexOf("'");
        int a = Integer.valueOf(result.substring(0, a_index));
        int a1 = Integer.valueOf(result.substring(0, a1_index));
        int a0 = Integer.valueOf(result.substring(a1_index, a_index));
        this.result = result;
    }

    //将分子分母调整之后，存储到成员变量中
    public void setAB(int a, int b) {

        this.result = result;
    }


    /**
     * 生成一个计算数
     *
     * @Return java.lang.String
     * @Author 林泽鸿
     * @Date 2020/4/9 23:35
     **/
    public static Fraction generateFraction() {
        //a.b 都是大于等于0的
        int a = GenerateUtils.getRandomInRange(Expression.range);
        int b = GenerateUtils.getRandomInRange(Expression.range);
        //分母为0
        while (b == 0) {
            b = GenerateUtils.getRandomInRange(Expression.range);
        }
        Fraction result = new Fraction(numberUtils.changeNumber(a, b));
        return result;
    }


    //加法
    public static String add(String left_result, String right_result) {
        // a/b+c/d =（ad+bc）/bd


        return "";
    }

    //求最大公因数，辗转相除法
    private int gcd(int a, int b) {
        int mod = a % b;
        return mod == 0 ? b : gcd(b, mod);
    }


    //看当前分数是否为负数
    boolean isNegative() {
        return false;
    }
}

