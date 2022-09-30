

/*
 * 56. Merge Intervals
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * Example 1:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 *
 * Example 2:
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * Constraints:
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 * */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    Solution() {}

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        List<int[]> list = new ArrayList<>();
        int start = intervals[0][0], end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (end < intervals[i][0]) {
                list.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
            else {
                start = Math.min(start, intervals[i][0]);
                end = Math.max(end, intervals[i][1]);
            }
        }
        list.add(new int[]{start, end});
        return list.toArray(new int[list.size()][]);
    }

}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();

//         test 1 [] guarantee 1 <= intervals.length <= 10^4
        int[][] i1 = new int[][] {};
        System.out.println(Arrays.deepToString(solution.merge(i1)));
//         test 2 [[1,3]]
        int[][] i2 = new int[][] {new int[] {1,3}};
        System.out.println(Arrays.deepToString(solution.merge(i2)));
//         test 2-1 [[1,4],[2,3]] -> [1,4]  长棒完全覆盖率短棒
        int[][] i21 = new int[][] {new int[] {1,4}, new int[] {2,3}};
        System.out.println(Arrays.deepToString(solution.merge(i21)));
//         test 3  [[1,4],[4,5]]
        int[][] i3 = new int[][] {new int[] {1,4}, new int[] {4,5}};
        System.out.println(Arrays.deepToString(solution.merge(i3)));
//         test 4 [1,3],[2,6],[8,10],[15,18] -> [1,6],[8,10],[15,18]  一棒部分覆盖二棒
        int[][] i4 = new int[][] {new int[] {1,3}, new int[] {2,6}, new int[] {8,10}, new int[] {15,18}};
        System.out.println(Arrays.deepToString(solution.merge(i4)));
        // test 5 [1,4],[0,5],[5,10],[12,18] -> [0,10],[15,18]
        int[][] i5 = new int[][] {new int[] {1,4}, new int[] {0,5}, new int[] {5,10}, new int[] {12,18}};
        System.out.println(Arrays.deepToString(solution.merge(i5)));
    }
}