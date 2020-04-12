package com.lzh;

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

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    //设置分子和分母
    public Fraction(int a, int b) {
        setAB(a, b);
    }

    //通过表达式得到分子和分母,都未经过简化，分母可能为0
    public Fraction(String result) {
        result.trim();
        int a_index = result.indexOf("/");
        int a1_index = result.indexOf("'");

        //不是分式的时候
        if (a_index == -1) {
            a = Integer.valueOf(result);
        }
        //是分式的时候
        else {
            //分母
            b = Integer.valueOf(result.substring(a_index + 1));
            //真分数
            if (a1_index == -1) {
                a = Integer.valueOf(result.substring(0, a_index));
            }
            //带分数
            else {
                int a1 = Integer.valueOf(result.substring(0, a1_index));
                int a0 = Integer.valueOf(result.substring(a1_index + 1, a_index));
                a = a1 * b + a0;
            }
        }
        setAB(a, b);
    }

    //将分子分母调整之后，存储到成员变量中
    public void setAB(int a, int b) {
        if (b == 0)
            throw new RuntimeException("分母不能为0");
        //结果默认是正数
        int isNagitiveAB = 1;
        //调整符号，b只能为正数
        if (a * b < 0) {
            isNagitiveAB = -1;
        }
        a = Math.abs(a);
        b = Math.abs(b);
        //最大公因数
        int g = gcd(a, b);
        //化简
        this.a = a * isNagitiveAB / g;
        this.b = b / g;

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
        Fraction result = new Fraction(a, b);
        return result;
    }


    //加法
    public Fraction add(Fraction right) {
        // a/b+c/d =（ad+bc）/bd
        return new Fraction(
                this.a * right.b + this.b * right.a,
                this.b * right.b
        );
    }

    //减法
    public Fraction subtract(Fraction right) {
        // a/b-c/d =（ad-bc）/bd
        return new Fraction(
                this.a * right.b - this.b * right.a,
                this.b * right.b
        );
    }

    //乘法
    public Fraction multiply(Fraction right) {
        // a/b * c/d = ac / bd
        return new Fraction(
                this.a * right.a,
                this.b * right.b
        );
    }

    //乘法
    public Fraction divide(Fraction right) {
        // a/b  /  c/d = ad / bc
        return new Fraction(
                this.a * right.b,
                this.b * right.a
        );
    }

    //

    //求最大公因数，辗转相除法
    private int gcd(int a, int b) {
        int big = a;
        if (big == 0)
            return 1;
        int small = b;
        //让a成为最大的
        if (a < b) {
            big = b;
            small = a;
        }
        int mod = big % small;
        return mod == 0 ? small : gcd(small, mod);
    }


    //看当前分数是否为负数
    boolean isNegative() {
        //结果默认是正数
        boolean isNagitiveAB = false;
        if (a * b < 0) {
            isNagitiveAB = true;
        }
        return isNagitiveAB;
    }


    //将a,b转化为表达式
    @Override
    public String toString() {
        //不是分式
        if (b == 1)
            return String.valueOf(a);
            //真分式
        else {
            int i = a / b;
            //余数
            int j = a % b;
            if (i != 0) {
                return String.format("%d'%d/%d", i, j, b);
            } else {
                return String.format("%d/%d", a, b);
            }
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        if (a != fraction.a) return false;
        return b == fraction.b;
    }

    //根据分子和分母
    @Override
    public int hashCode() {
        int result = 31 * a + b;
        return result;
    }
}

