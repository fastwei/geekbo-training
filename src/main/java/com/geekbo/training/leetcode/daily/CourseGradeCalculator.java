package com.geekbo.training.leetcode.daily;

public class CourseGradeCalculator {
    public static void main(String[] args) {
        double writtenExamScore = 50; // 笔试分数
        double unitTestAverageScore = 100; // 单元测试平均分

        // 计算课程总评成绩
        double courseGrade = writtenExamScore * 0.6 + unitTestAverageScore * 0.4;

        System.out.println("课程总评成绩：" + courseGrade);
    }
}