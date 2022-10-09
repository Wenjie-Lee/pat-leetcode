/* 1. Two Sum
 * 给定一个整数数组，从中取两个数和为k，输出这两个数的索引
 * 题目给定输入确保有且仅有一种输出
 * and you may not use the same element twice
 * 意思是从前到后遍历时，使用过的元素不会产生所需的结果
 * */

/*
 * 解法：
 * 1：由于确保每个数只出现一次，可以使用HashMap记录数和索引
 * 2：排序后，双针法
 * */

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

class Solution {
    // there is no need to sorting the nums
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (map.containsKey(nums[i])) {
                int left = map.get(nums[i]);
                return new int[] {left, i};
            } else {
                map.put(target - nums[i], i);
            }
        }
        return new int[2];
    }

    // HashMap and two pointers makes time cost to O(n)
    // but sorting the nums makes it O(nlogn) in average
    public int[] twoSum2(int[] nums, int target) {
        // use a HashMap to maintain the order before sorted
        // a LinkedList is to maintain the list of conflict indices of same key
        HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new LinkedList<>());
            }
            map.get(nums[i]).add(i);
        }
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        int[] indices = new int[2];
        while (i < j) {
            if (nums[i] + nums[j] == target) {
                indices[0] = map.get(nums[i]).pop();
                indices[1] = map.get(nums[j]).pop();
                break;
            } else if (nums[i] + nums[j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return indices;
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();

        // test 1:
        System.out.println(Arrays.toString(s.twoSum(new int[]{1, 2}, 3)));
        // test 2: Input: nums = [2,7,11,15], target = 9, Output: [0,1]
        System.out.println(Arrays.toString(s.twoSum(new int[]{2,7,11,15}, 9)));
        // test 3: Input: nums = [3,2,4], target = 6, Output: [1,2]
        System.out.println(Arrays.toString(s.twoSum(new int[]{3,2,4}, 6)));
        // test 4: Input: nums = [3,3], target = 6, Output: [0,1]
        System.out.println(Arrays.toString(s.twoSum(new int[]{3,3}, 6)));
    }
}