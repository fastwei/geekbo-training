package com.geekbo.training.leetcode.design;

import java.util.Iterator;

/**
 * 284. Peeking Iterator
 * Medium
 * Design an iterator that supports the peek operation on an existing iterator in addition to the hasNext and the next operations.
 * <p>
 * Implement the PeekingIterator class:
 * <p>
 * PeekingIterator(Iterator<int> nums) Initializes the object with the given integer iterator iterator.
 * int next() Returns the next element in the array and moves the pointer to the next element.
 * boolean hasNext() Returns true if there are still elements in the array.
 * int peek() Returns the next element in the array without moving the pointer.
 * Note: Each language may have a different implementation of the constructor and Iterator, but they all support the int next() and boolean hasNext() functions.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * Output
 * [null, 1, 2, 2, 3, false]
 * <p>
 * Explanation
 * PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [1,2,3]
 * peekingIterator.next();    // return 1, the pointer moves to the next element [1,2,3].
 * peekingIterator.peek();    // return 2, the pointer does not move [1,2,3].
 * peekingIterator.next();    // return 2, the pointer moves to the next element [1,2,3]
 * peekingIterator.next();    // return 3, the pointer moves to the next element [1,2,3]
 * peekingIterator.hasNext(); // return False
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * All the calls to next and peek are valid.
 * At most 1000 calls will be made to next, hasNext, and peek.
 * <p>
 * <p>
 * Follow up: How would you extend your design to be generic and work with all types, not just integer?
 */
import java.util.Iterator;

class PeekingIterator<T> implements Iterator<T> {
    private Iterator<T> iterator;
    private T nextElement;
    private boolean hasNext;

    public PeekingIterator(Iterator<T> iterator) {
        this.iterator = iterator;
        this.nextElement = null;
        this.hasNext = iterator.hasNext();
        if (this.hasNext) {
            this.nextElement = iterator.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public T peek() {
        return nextElement;
    }

    // Returns the next element in the iteration and advances the iterator.
    @Override
    public T next() {
        T temp = nextElement;
        hasNext = iterator.hasNext();
        if (hasNext) {
            nextElement = iterator.next();
        } else {
            nextElement = null;
        }
        return temp;
    }

    // Returns true if the iteration has more elements.
    @Override
    public boolean hasNext() {
        return hasNext;
    }
}