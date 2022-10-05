/**
 * @author youzh
 * @version 1.0
 * @project pat-leetcode
 * @description Solution
 * @date 2022/9/29 20:10:46
 */


/* 136
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
You must implement a solution with a linear runtime complexity and use only constant extra space.
给定一个非空整数数组，数组中有一个元素只出现一次，其他元素都出现两次，找到那个元素，时间O(N)，空间O(1)

Example 1:
Input: nums = [2,2,1]
Output: 1

Example 2:
Input: nums = [4,1,2,1,2]
Output: 4

Example 3:
Input: nums = [1]
Output: 1

Constraints:
1 <= nums.length <= 3 * 10^4
-3 * 10^4 <= nums[i] <= 3 * 10^4
Each element in the array appears twice except for one element which appears only once.
* */
public class Solution {
    public int singleNumber(int[] nums) {
        if (nums.length == 1) return nums[0];
        // 异或，a^a = 0
        // 所有数都做^操作，结果就是只出现一次的数
        // 此方法仅当所有其他数出现次数为偶数时
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }
}

class Test {
    public static void main(String[] args) {

        Solution solution = new Solution();
        // [1]
        int[] i1 = new int[]{1};
        System.out.println(solution.singleNumber(i1));
        // [1,2,2]
        int[] i2 = new int[]{1,2,2};
        System.out.println(solution.singleNumber(i2));
        // [4,1,2,1,2]
        int[] i3 = new int[]{4,1,2,1,2};
        System.out.println(solution.singleNumber(i3));
        // [-1,2,3,2,-1]
        int[] i4 = new int[]{-1,2,3,2,-1};
        System.out.println(solution.singleNumber(i4));
    }
}
