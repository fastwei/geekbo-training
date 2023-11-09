package com.geekbo.training.datastructure;

/**
 * 641. Design Circular Deque
 * <p>
 * Medium
 * <p>
 * Design your implementation of the circular double-ended queue (deque).
 * <p>
 * Implement the MyCircularDeque class:
 * <p>
 * MyCircularDeque(int k) Initializes the deque with a maximum size of k.
 * boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful, or false otherwise.
 * boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful, or false otherwise.
 * int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
 * int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
 * boolean isEmpty() Returns true if the deque is empty, or false otherwise.
 * boolean isFull() Returns true if the deque is full, or false otherwise.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * Output
 * [null, true, true, true, false, 2, true, true, true, 4]
 * <p>
 * Explanation
 * MyCircularDeque myCircularDeque = new MyCircularDeque(3);
 * myCircularDeque.insertLast(1);  // return True
 * myCircularDeque.insertLast(2);  // return True
 * myCircularDeque.insertFront(3); // return True
 * myCircularDeque.insertFront(4); // return False, the queue is full.
 * myCircularDeque.getRear();      // return 2
 * myCircularDeque.isFull();       // return True
 * myCircularDeque.deleteLast();   // return True
 * myCircularDeque.insertFront(4); // return True
 * myCircularDeque.getFront();     // return 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * At most 2000 calls will be made to insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull.
 */
class MyCircularDeque {
    private int[] deque;
    private int front;
    private int rear;
    private int size;

    public MyCircularDeque(int k) {
        deque = new int[k];
        front = 0;
        rear = 0;
        size = 0;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        front = (front - 1 + deque.length) % deque.length;
        deque[front] = value;
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        deque[rear] = value;
        rear = (rear + 1) % deque.length;
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % deque.length;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        rear = (rear - 1 + deque.length) % deque.length;
        size--;
        return true;
    }

    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return deque[front];
    }

    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return deque[(rear - 1 + deque.length) % deque.length];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == deque.length;
    }
}