package com.geekbo.training.leetcode.daily;

import java.util.HashSet;

/**
 * 391. Perfect Rectangle
 * Hard
 * Given an array rectangles where rectangles[i] = [xi, yi, ai, bi] represents an axis-aligned rectangle. The bottom-left point of the rectangle is (xi, yi) and the top-right point of it is (ai, bi).
 * <p>
 * Return true if all the rectangles together form an exact cover of a rectangular region.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: rectangles = [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
 * Output: true
 * Explanation: All 5 rectangles together form an exact cover of a rectangular region.
 * Example 2:
 * <p>
 * <p>
 * Input: rectangles = [[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]
 * Output: false
 * Explanation: Because there is a gap between the two rectangular regions.
 * Example 3:
 * <p>
 * <p>
 * Input: rectangles = [[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]
 * Output: false
 * Explanation: Because two of the rectangles overlap with each other.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= rectangles.length <= 2 * 104
 * rectangles[i].length == 4
 * -105 <= xi, yi, ai, bi <= 105
 */
class PerfectRectangle {
    public boolean isRectangleCover(int[][] rectangles) {
        HashSet<String> hs = new HashSet<>();
        int area = 0;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxA = Integer.MIN_VALUE;
        int maxB = Integer.MIN_VALUE;
        for (int i = 0; i < rectangles.length; i++) {
            int x = rectangles[i][0];
            int y = rectangles[i][1];
            int a = rectangles[i][2];
            int b = rectangles[i][3];
            area += Math.abs(y - b) * Math.abs(x - a);
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxA = Math.max(maxA, a);
            maxB = Math.max(maxB, b);
            String bottomLeft = x + ":" + y;
            String bottomRight = a + ":" + y;
            String topLeft = x + ":" + b;
            String topRight = a + ":" + b;
            if (!hs.contains(bottomLeft))
                hs.add(bottomLeft);
            else
                hs.remove(bottomLeft);
            if (!hs.contains(bottomRight))
                hs.add(bottomRight);
            else
                hs.remove(bottomRight);
            if (!hs.contains(topLeft))
                hs.add(topLeft);
            else
                hs.remove(topLeft);
            if (!hs.contains(topRight))
                hs.add(topRight);
            else
                hs.remove(topRight);
        }
        String FullbottomLeft = minX + ":" + minY;
        String FullbottomRight = maxA + ":" + minY;
        String FulltopLeft = minX + ":" + maxB;
        String FulltopRight = maxA + ":" + maxB;
        if (!(hs.size() == 4) || !hs.contains(FullbottomLeft) || !hs.contains(FullbottomRight) || !hs.contains(FulltopRight) || !hs.contains(FulltopLeft))
            return false;
        int newarea = Math.abs(minX - maxA) * Math.abs(minY - maxB);
        if (newarea == area)
            return true;
        return false;

    }

    public static void main(String[] args) {
        PerfectRectangle solution = new PerfectRectangle();

        int[][] rectangles1 = {{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}};
        System.out.println(solution.isRectangleCover(rectangles1)); // Expected: true

        int[][] rectangles2 = {{1, 1, 2, 3}, {1, 3, 2, 4}, {3, 1, 4, 2}, {3, 2, 4, 4}};
        System.out.println(solution.isRectangleCover(rectangles2)); // Expected: false

        int[][] rectangles3 = {{1, 1, 3, 3}, {3, 1, 4, 2}, {1, 3, 2, 4}, {2, 2, 4, 4}};
        System.out.println(solution.isRectangleCover(rectangles3)); // Expected: false
    }
}
