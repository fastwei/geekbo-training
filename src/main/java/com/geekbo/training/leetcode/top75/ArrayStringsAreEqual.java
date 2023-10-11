package com.geekbo.training.leetcode.top75;


/**
 *Array / String
 *
 * Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.
 *
 * A string is represented by an array if the array elements concatenated in order forms the string.
 *
 * 分析代码的时间复杂度如下：
 *
 * 首先，代码中的 String.join("", word1) 和 String.join("", word2) 用于将字符串数组的元素连接成一个字符串，这两个操作的时间复杂度是线性的，即O(N)，其中N是字符串数组中元素的总数。
 *
 * 接下来，代码执行了 concatenatedWord1.equals(concatenatedWord2) 操作，这是两个字符串的比较操作。字符串比较的时间复杂度通常是O(M)，其中M是两个字符串中较短的那个字符串的长度。
 *
 * 因此，整个代码的时间复杂度主要由字符串连接和字符串比较的操作决定。最坏情况下，时间复杂度可以近似看作O(N+M)，其中N和M分别是两个输入字符串数组的总字符数。在实际情况下，通常N和M是相对较小的，因此可以认为该算法的时间复杂度是线性的，即O(N+M)。
 *
 * 需要注意的是，如果输入的字符串数组非常大，其中包含大量的字符，那么字符串连接和比较操作的时间开销可能会显著增加。
 *
 *
 * 提供的算法是一个普通的字符串拼接比较算法，时间复杂度为O(N+M)，其中N和M分别是两个字符串的长度。要实现更好的时间复杂度，可以考虑以下优化方案：
 *
 * 使用KMP算法(参考StringMatch)：KMP算法是一种高效的字符串匹配算法，可以在O(N+M)的时间内找到两个字符串的最大公因子。首先，将两个字符串拼接起来，然后使用KMP算法找到最长的公共前缀，这个前缀就是最大公因子。KMP算法的时间复杂度为O(N+M)。
 *
 * 求两个字符串长度的最大公约数：可以使用辗转相除法等方法来求两个字符串长度的最大公约数，然后根据最大公约数来截取子串，再进行比较。这样可以减少不必要的拼接操作，从而提高效率。
 *
 * 使用哈希算法：将字符串映射成一个唯一的哈希值，然后比较哈希值是否相等。这种方法可以在常数时间内完成比较，但需要处理哈希冲突。
 *
 * 这些方法中，KMP算法是最常用的，也是时间复杂度最优的方法。它能够在线性时间内完成字符串匹配，适用于解决这类问题。其他方法可能会涉及到更复杂的处理，因此KMP算法是一个值得考虑的优化方案。
 *
 */
public class ArrayStringsAreEqual {
    public static void main(String[] args) {
        // Test cases
        String[] word1 = {"ab", "c"};
        String[] word2 = {"a", "bc"};
        System.out.println(arrayStringsAreEqual(word1, word2)); // Output: true

        String[] word3 = {"a", "cb"};
        String[] word4 = {"ab", "c"};
        System.out.println(arrayStringsAreEqual(word3, word4)); // Output: false

        String[] word5 = {"abc", "d", "defg"};
        String[] word6 = {"abcddefg"};
        System.out.println(arrayStringsAreEqual(word5, word6)); // Output: true
    }

    public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        // Concatenate the elements of both arrays and compare the resulting strings
        String concatenatedWord1 = String.join("", word1);
        String concatenatedWord2 = String.join("", word2);

        return concatenatedWord1.equals(concatenatedWord2);
    }
}
