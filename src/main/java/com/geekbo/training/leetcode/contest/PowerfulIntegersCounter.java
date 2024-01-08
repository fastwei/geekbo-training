package com.geekbo.training.leetcode.contest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PowerfulIntegersCounter {

    /**
     * 计算区间[start, finish]中满足条件的强整数个数
     *
     * @param start  起始值
     * @param finish 结束值
     * @param limit  限制条件
     * @param s      字符串s
     * @return 满足条件的强整数个数
     */
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        long sVal = Long.parseLong(s);

        long mul = 1;

        while (mul <= sVal) {
            mul *= 10;
        }

        long result = solve(finish, sVal, mul, limit);
        result -= solve(start - 1, sVal, mul, limit);

        return result;
    }

    /**
     * 将一个十进制整数转换为指定基数的十进制
     *
     * @param val  十进制整数
     * @param base 基数
     * @return 转换后的十进制数
     */
    private double convertBase(long val, long base) {
        double result = 0;
        List<Integer> results = new ArrayList<>();
        int mod;

        while (val != 0) {
            mod = (int) (val % base);
            results.add(mod);
            val /= base;
        }

        Collections.reverse(results);

        for (int num : results) {
            result *= 10;
            result += num;
        }

        return result;
    }

    /**
     * 在指定范围内查找满足条件的最大值
     *
     * @param val   结束值
     * @param s     字符串s
     * @param mul   倍数
     * @param limit 限制条件
     * @return 满足条件的最大值
     */
    private long solve(long val, long s, long mul, int limit) {
        long max = 1;

        while (max * mul <= val) {
            max *= 10;
        }

        long min = -1;

        long mid;
        double mVal;

        while (max - min > 1) {
            mid = (max + min) / 2;
            mVal = convertBase(mid, limit + 1) * mul + s;

            if (mVal <= val) {
                min = mid;
            } else {
                max = mid;
            }
        }

        return max;
    }
}