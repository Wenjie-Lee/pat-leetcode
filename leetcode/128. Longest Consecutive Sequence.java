/*128. Longest Consecutive Sequence
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 *
 * Constraints:
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * */

/*
 * 从给定无序数组中，找到最长的连续序列，要求时间复杂度O(n)
 * 所以sort(O(nlogn))，TreeSet(O(nlogk))不能使用
 * */

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

class Solution {
    // use remove() to make sure each element only has been visited once,which means TC is O(n)
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int maxCount = 1;
        for (int num : nums) {
            int consecutiveCount = 1;
            // look left
            int cur = num;
            while (set.contains(--cur)) {
                consecutiveCount++;
                set.remove(cur);    // remove elements visited
            }
            // look right
            cur = num;
            while (set.contains(++cur)) {
                consecutiveCount++;
                set.remove(cur);
            }
            // remove self, not an indispensable step
            set.remove(num);
            maxCount = Math.max(consecutiveCount, maxCount);
        }
        return maxCount;
    }

    // TreeSet construction process is O(nlogn), exceed O(n)
    public int longestConsecutive1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int consecutiveCount = 1, maxCount = 1, before = Integer.MIN_VALUE;
        for (Integer it : set) {
            if (it == before + 1) {
                consecutiveCount += 1;
            } else {
                consecutiveCount = 1;
            }
            before = it;
            if (consecutiveCount > maxCount)
                maxCount = consecutiveCount;
        }
        return maxCount;
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();

        // test0 nums = [0,0,0,0], output: 1
//        System.out.println(s.longestConsecutive(new int[] {0,0,0,0}));
        // test1 nums = [100,4,200,1,3,2], output: 4; [1, 2, 3, 4]
        System.out.println(s.longestConsecutive(new int[] {100,4,200,1,3,2}));
        // test2 nums = [0,3,7,2,5,8,4,6,0,1], output: 9
        System.out.println(s.longestConsecutive(new int[] {0,3,7,2,5,8,4,6,0,1}));
        // test3 nums = [1,2,3,4,5,6,7,8,9], output: 9
        System.out.println(s.longestConsecutive(new int[] {1,2,3,4,5,6,7,8,9}));
    }
}