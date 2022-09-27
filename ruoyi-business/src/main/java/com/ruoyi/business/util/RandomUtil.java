package com.ruoyi.business.util;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @program: ruoyi
 * @description: 生成随机数
 * @author: zjk
 * @create: 2022-04-28 10:58
 **/
public class RandomUtil {
    /**
     * 获取随机数
     * @param min 最小值
     * @param max 最大值
     * @return
     */
    public static int randomUtil(int min, int max) {
        Random random = new Random();
        IntStream ints = random.ints(1, min, max + 1);
        int[] ints1 = ints.toArray();
        return ints1[0];
    }

    /**
     * 获取当前时间戳
     * @return 4个字节的时间戳
     */
    public static int getCurrentTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
