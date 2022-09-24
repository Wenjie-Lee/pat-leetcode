/*
368. Largest Divisible Subset

Given a set of ***distinct*** positive integers nums, return the largest subset answer such that every pair (answer[i],
answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.

Example 1:
Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.

Example 2:
Input: nums = [1,2,4,8]
Output: [1,2,4,8]

Constraints:
1 <= nums.length <= 1000
1 <= nums[i] <= 2 * 109
All the integers in nums are unique.
* */

/*

1. 求最大的子集
2. 都是正整数，不存在重复数字
3. 子集中每两个数都满足：一个是另一个的因数 answer[i] % answer[j] == 0 || answer[j] % answer[i] == 0
4. 可能有多个结果，只需要返回其中一个

可得：
1. 动态规划：
2. 先对数组升序排序
3. 子问题：求子集德最大长度，max{f(x), f(k)+1 | k<x,a[x]%a[k]==0}, dp[]记录nums各个数字索引下的子集最长长度

* */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    static final int LENGTH = 1000;
    static final int MAX = 2 * 1000000000;


    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int[] from = new int[len];
        Arrays.sort(nums);      // sort ascend

        int max = 0, pos = -1;
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            from[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && dp[i] <  dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    from[i] = j;
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                pos = i;
            }
        }

        List<Integer> path = new ArrayList<>();
        for (int i = pos; i != -1; i = from[i]) {
            path.add(nums[i]);
        }

        return path;
    }


}