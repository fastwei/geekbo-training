package com.geekbo.training.leetcode.codeinterview109;

import java.util.LinkedList;


/**
 * 面试题 03.06. 动物收容所
 * 简单
 * 动物收容所。有家动物收容所只收容狗与猫，且严格遵守“先进先出”的原则。在收养该收容所的动物时，收养人只能收养所有动物中“最老”（由其进入收容所的时间长短而定）的动物，或者可以挑选猫或狗（同时必须收养此类动物中“最老”的）。换言之，收养人不能自由挑选想收养的对象。请创建适用于这个系统的数据结构，实现各种操作方法，比如enqueue、dequeueAny、dequeueDog和dequeueCat。允许使用Java内置的LinkedList数据结构。
 * <p>
 * enqueue方法有一个animal参数，animal[0]代表动物编号，animal[1]代表动物种类，其中 0 代表猫，1 代表狗。
 * <p>
 * dequeue*方法返回一个列表[动物编号, 动物种类]，若没有可以收养的动物，则返回[-1,-1]。
 * <p>
 * 解题思路：
 * <p>
 * 使用两个LinkedList分别存储猫和狗。
 * enqueue方法根据动物的种类将其加入到对应的LinkedList中。
 * dequeueAny方法先检查猫和狗的LinkedList是否都不为空，如果是，则比较两个LinkedList的头部动物的编号，返回编号较小的动物；如果只有一个LinkedList为空，直接返回该LinkedList的头部动物；如果两个LinkedList都为空，返回[-1, -1]。
 * dequeueDog和dequeueCat方法分别从狗和猫的LinkedList中取出头部的动物，如果LinkedList为空，则返回[-1, -1]。
 * <p>
 * 算法复杂度分析：
 * <p>
 * enqueue方法的时间复杂度是O(1)。
 * dequeueAny、dequeueDog和dequeueCat方法的时间复杂度也是O(1)。
 * 空间复杂度是O(n)，其中n是收容所中的动物数量。
 */
class AnimalShelter {
    private LinkedList<int[]> cats;
    private LinkedList<int[]> dogs;

    public AnimalShelter() {
        cats = new LinkedList<>();
        dogs = new LinkedList<>();
    }

    public void enqueue(int[] animal) {
        if (animal[1] == 0) {
            cats.offer(animal);
        } else {
            dogs.offer(animal);
        }
    }

    public int[] dequeueAny() {
        if (!cats.isEmpty() && !dogs.isEmpty()) {
            if (cats.peek()[0] < dogs.peek()[0]) {
                return cats.poll();
            } else {
                return dogs.poll();
            }
        } else if (!cats.isEmpty()) {
            return cats.poll();
        } else if (!dogs.isEmpty()) {
            return dogs.poll();
        } else {
            return new int[]{-1, -1};
        }
    }

    public int[] dequeueDog() {
        if (!dogs.isEmpty()) {
            return dogs.poll();
        } else {
            return new int[]{-1, -1};
        }
    }

    public int[] dequeueCat() {
        if (!cats.isEmpty()) {
            return cats.poll();
        } else {
            return new int[]{-1, -1};
        }
    }

    public static void main(String[] args) {
        AnimalShelter shelter = new AnimalShelter();

        shelter.enqueue(new int[]{0, 0});
        shelter.enqueue(new int[]{1, 0});
        int[] cat = shelter.dequeueCat();
        int[] dog = shelter.dequeueDog();
        int[] any = shelter.dequeueAny();

        System.out.println("Cat: " + cat[0] + ", " + cat[1]);
        System.out.println("Dog: " + dog[0] + ", " + dog[1]);
        System.out.println("Any: " + any[0] + ", " + any[1]);
    }
}
