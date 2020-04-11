package com.lzh;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description 生成四则表达式
 * @Author 林泽鸿
 * @Date 2020/4/7 18:31
 */
public class GenerateUtils {

    /**
     * 获得范围内的随机整数
     *
     * @Param range  范围
     * @Return int 随机数
     * @Author 林泽鸿
     * @Date 2020/4/9 22:11
     **/
    public static int getRandomInRange(int range) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(range);
    }


    //生成题目和答案的映射关系
    public static HashMap<String, String> generateMap(int exam_number, int answer_range) {
        if (exam_number < 1) {
            throw new RuntimeException("生成题目的个数必须大于0");
        }
        if (answer_range < 1) {
            throw new RuntimeException("运算结果范围必须大于等于1");
        }
        HashMap<String, String> hashMap = new HashMap<>();

        for (int i = 1; hashMap.size() < exam_number; ) {
            //因为在运算的过程中会出现n÷0的情况，这时候就会抛异常
            Expression expression = new Expression(3, answer_range);
            if ((hashMap.get(expression.toString()) != null || !"".equals(expression.toString()))
                    &&
                    !expression.isDivideForZero()) {
                hashMap.put(expression.toString(), expression.getRoot().result.toString());
                i++;
            }
        }
        return hashMap;
    }
}
