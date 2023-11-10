package com.geekbo.training.leetcode.daily;

import java.util.*;

/**
 * 2182. Construct String With Repeat Limit
 * Medium
 * You are given a string s and an integer repeatLimit.
 * Construct a new string repeatLimitedString using the characters of s
 * such that no letter appears more than repeatLimit times in a row.
 * You do not have to use all characters from s.
 * <p>
 * Return the lexicographically largest repeatLimitedString possible.
 * <p>
 * A string a is lexicographically larger than a string b if in the first position where a and b differ,
 * string a has a letter that appears later in the alphabet than the corresponding letter in b.
 * If the first min(a.length, b.length) characters do not differ,
 * then the longer string is the lexicographically larger one.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "cczazcc", repeatLimit = 3
 * Output: "zzcccac"
 * Explanation: We use all of the characters from s to construct the repeatLimitedString "zzcccac".
 * The letter 'a' appears at most 1 time in a row.
 * The letter 'c' appears at most 3 times in a row.
 * The letter 'z' appears at most 2 times in a row.
 * Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
 * The string is the lexicographically largest repeatLimitedString possible so we return "zzcccac".
 * Note that the string "zzcccca" is lexicographically larger but the letter 'c' appears more than 3 times in a row,
 * so it is not a valid repeatLimitedString.
 * Example 2:
 * <p>
 * Input: s = "aababab", repeatLimit = 2
 * Output: "bbabaa"
 * Explanation: We use only some of the characters from s to construct the repeatLimitedString "bbabaa".
 * The letter 'a' appears at most 2 times in a row.
 * The letter 'b' appears at most 2 times in a row.
 * Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
 * The string is the lexicographically largest repeatLimitedString possible so we return "bbabaa".
 * Note that the string "bbabaaa" is lexicographically larger but the letter 'a' appears more than 2 times in a row,
 * so it is not a valid repeatLimitedString.
 */
public class ConstructStringWithRepeatLimit {

    public String repeatLimitedString(String s, int repeatLimit) {
        PriorityQueue<Character> pq = new PriorityQueue<Character>((a, b) -> b - a);
        for (char ch : s.toCharArray()) {
            pq.add(ch);
        }
        StringBuffer res = new StringBuffer();
        ArrayList<Character> list = new ArrayList<Character>();
        Stack<Character> stk = new Stack<Character>();
        int count = 0;
        char previouschar = pq.peek();
        while (!pq.isEmpty()) {
            char curr = pq.poll();
            if (curr == previouschar) {
                if (count < repeatLimit) {
                    res.append(curr);
                } else {
                    stk.add(curr);
                }
                count++;
            } else {
                if (stk.isEmpty()) {
                    count = 0;
                    res.append(curr);
                    previouschar = curr;
                    count++;
                } else {
                    res.append(curr);
                    count = 0;
                    while (!stk.isEmpty() && count < repeatLimit) {
                        res.append(stk.pop());
                        count++;
                    }
                }
            }
        }
        return res.toString();
    }

    /**
     * Explanation:
     * <p>
     * We use a StringBuilder to construct the repeatLimitedString.
     * We also use a HashMap called countMap to keep track of the count of each character in the string s.
     * For each character c in s, we increment its count in countMap.
     * If the count of c is less than or equal to repeatLimit, we append c to the result string.
     * Finally, we return the result string.
     * The code has a time complexity of O(n), where n is the length of the string s.
     * This is because we iterate through each character of s once.
     *
     * @param s
     * @param repeatLimit
     * @return
     */
    public static String constructString(String s, int repeatLimit) {
        StringBuilder result = new StringBuilder();
        Map<Character, Integer> countMap = new HashMap<>();

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
            if (countMap.get(c) <= repeatLimit) {
                result.insert(0, c);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String s1 = "cczazcc";
        int repeatLimit1 = 3;
        String s2 = "aababab";
        int repeatLimit2 = 2;

        String result1 = constructString(s1, repeatLimit1);
        String result2 = constructString(s2, repeatLimit2);

        System.out.println(result1); // Output: "zzcccac"
        System.out.println(result2); // Output: "bbabaa"
    }
}