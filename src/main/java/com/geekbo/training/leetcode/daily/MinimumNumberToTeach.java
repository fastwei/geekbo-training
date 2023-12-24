package com.geekbo.training.leetcode.daily;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1733. Minimum Number of People to Teach
 * Medium
 * On a social network consisting of m users and some friendships between users, two users can communicate with each other if they know a common language.
 * <p>
 * You are given an integer n, an array languages, and an array friendships where:
 * <p>
 * There are n languages numbered 1 through n,
 * languages[i] is the set of languages the i​​​​​​th​​​​ user knows, and
 * friendships[i] = [u​​​​​​i​​​, v​​​​​​i] denotes a friendship between the users u​​​​​​​​​​​i​​​​​ and vi.
 * You can choose one language and teach it to some users so that all friends can communicate with each other. Return the minimum number of users you need to teach.
 * <p>
 * Note that friendships are not transitive, meaning if x is a friend of y and y is a friend of z, this doesn't guarantee that x is a friend of z.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
 * Output: 1
 * Explanation: You can either teach user 1 the second language or user 2 the first language.
 * Example 2:
 * <p>
 * Input: n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
 * Output: 2
 * Explanation: Teach the third language to users 1 and 3, yielding two users to teach.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= n <= 500
 * languages.length == m
 * 1 <= m <= 500
 * 1 <= languages[i].length <= n
 */
public class MinimumNumberToTeach {

    public static int minimumTeachings(int n, int[][] languages, int[][] friendships) {

        Map<Integer, Set<Integer>> langSpeakersMap = new HashMap<>();

        for (int i = 0; i < languages.length; i++) {
            int[] langs = languages[i];
            for (int lang : langs) {
                if (langSpeakersMap.get(lang) == null) {
                    HashSet<Integer> speakersSet = new HashSet<>();
                    speakersSet.add(i + 1);
                    langSpeakersMap.put(lang, speakersSet);
                } else {
                    langSpeakersMap.get(lang).add(i + 1);
                }
            }
        }

        Set<Integer> usersWhoCannotComEachOther = new HashSet<>();

        for (int[] friendship : friendships) {
            int user1 = friendship[0];
            int user2 = friendship[1];
            boolean found = false;
            for (int i = 0; i < languages[user1 - 1].length; i++) {//langs spoken by user1
                int lang = languages[user1 - 1][i];
                if (langSpeakersMap.get(lang).contains(user2)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                Collections.addAll(usersWhoCannotComEachOther, user1, user2);
            }
        }

        int ans = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Set<Integer>> entry : langSpeakersMap.entrySet()) {
            Set<Integer> value = entry.getValue();
            int counter = 0;
            for (Integer u : usersWhoCannotComEachOther) {
                if (!value.contains(u)) {
                    counter++;
                }
            }
            ans = Math.min(ans, counter);
        }

        return ans;
    }
}