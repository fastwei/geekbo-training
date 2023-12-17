package com.geekbo.training.leetcode.crackinterview109;

class StringRotation {
    /**
     * 字符串轮转
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString(String s1, String s2) {
        // 判断两个字符串的长度是否相等
        if (s1.length() != s2.length()) {
            return false;
        }

        // 将s1与自身连接起来形成一个新的字符串s1s1
        String s1s1 = s1 + s1;

        // 判断s2是否是s1s1的子串
        return s1s1.contains(s2);
    }
}