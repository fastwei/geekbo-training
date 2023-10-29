package com.geekbo.training.leetcode.template;

public class RecursionTemplate {

    public int recursiveFunction(int parameter) {
        // 递归终止条件
        if (parameter <= 0) {
            return 0;
        }

        // 处理当前层逻辑

        // 递归调用，进入下一层
        int result = recursiveFunction(parameter - 1);

        // 处理当前层的结果

        return result;
    }

    public static void main(String[] args) {
        RecursionTemplate template = new RecursionTemplate();
        int result = template.recursiveFunction(5);
        System.out.println(result);
    }
}