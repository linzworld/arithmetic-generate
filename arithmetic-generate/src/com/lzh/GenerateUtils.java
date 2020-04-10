package com.lzh;

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
}
