package com.geekbo.training.leetcode.template;

import java.util.Stack;

/**
 *
 * 我们使用Java的内置类Stack来实现栈的模板示例。
 * 在main函数中，我们创建了一个整数类型的栈对象stack。
 *
 * 然后我们进行了以下操作：
 *
 * 使用push方法将元素1、2、3依次入栈。
 * 使用pop方法弹出栈顶元素，并将其赋值给topElement变量。
 * 使用peek方法获取栈顶元素，并将其赋值给peekElement变量。
 * 使用isEmpty方法判断栈是否为空，并将结果赋值给isEmpty变量。
 * 使用size方法获取栈的大小，并将结果赋值给size变量。
 * 最后，我们打印出栈顶元素、栈是否为空、栈的大小等信息。
 *
 * 这个模板示例提供了一个基本的栈的实现，你可以根据需要对栈进行进一步的操作和扩展。
 *
 */
public class StackTemplate {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        // 入栈操作
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // 出栈操作
        int topElement = stack.pop();
        System.out.println("Popped element: " + topElement);

        // 获取栈顶元素
        int peekElement = stack.peek();
        System.out.println("Top element: " + peekElement);

        // 判断栈是否为空
        boolean isEmpty = stack.isEmpty();
        System.out.println("Is stack empty? " + isEmpty);

        // 获取栈的大小
        int size = stack.size();
        System.out.println("Stack size: " + size);
    }
}
