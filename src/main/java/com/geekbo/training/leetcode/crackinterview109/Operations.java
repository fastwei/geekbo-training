package com.geekbo.training.leetcode.crackinterview109;

class Operations {
    public Operations() {

    }

    //减法
    public int minus(int a, int b) {
        int sum = a;
        boolean flag = b>0;
        int temp = flag ? -1 : 1;
        while (b!=0) {
            if ((flag && b+temp>=0) || (flag == false && b+temp<=0)) {
                b += temp;
                sum += temp;
                temp += temp;//加速指向目标值
            } else {
                temp = flag ? -1 : 1;
            }
        }
        return sum;
        //   return a+(~b+1);//违规解法
    }

    //乘法
    public int multiply(int a, int b) {
        int sum = 0;
        int flag;
        int tempA;
        int tempB;
        if (a > 0) {
            flag = -1;
            tempA = -1;
            tempB = b;
        } else {
            flag = 1;
            tempA = 1;
            tempB = minus(0, b);
        }
        while (a != 0) {
            if ((flag < 0 && a + tempA >= 0) || (flag > 0 && a + tempA <= 0)) {
                sum += tempB;
                a += tempA;
                tempA = tempA + tempA;
                tempB = tempB + tempB;
            } else {
                tempA = flag;
                tempB = flag < 0 ? b : minus(0, b);
            }
        }
        return sum;
    }

    //除法
    public int divide(int a, int b) {//-5 -2

        int sum = 0;
        int tempB;
        boolean flag;
        if ((a > 0 && b > 0) || (a < 0 && b < 0)) {//a-b-b-b.....
            flag = true;
        } else {//a+b+b+b.....
            flag = false;
        }
        int absA = a >= 0 ? minus(0, a) : a;
        int absB = b <= 0 ? minus(0, b) : b;
        tempB = minus(0, absB);
        int tempSum = -1;
        int tempA = 0;
        while (absA + absB <= tempA) {
            if (absA <= tempA + tempB && Integer.MIN_VALUE - tempB <= tempA) {
                tempA += tempB;
                sum += tempSum;
                tempB += tempB;
                tempSum += tempSum;

            } else {
                tempSum = -1;
                tempB = minus(0, absB);
            }
        }
        if (!flag) {
            return sum;
        } else {
            return minus(0, sum);
        }
    }
}