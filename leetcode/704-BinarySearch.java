import java.util.Arrays;
import java.util.Comparator;

/*
 * 704. Binary Search
 * Given an array of integers nums which is sorted in ascending order, and an integer target,
 * write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
 * You must write an algorithm with O(log n) runtime complexity.
 * 给定一个升序整数数组，一个整数目标数，在数组中找到目标数，否则返回-1
 *
 * Example 1:
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 *
 * Example 2:
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 *
 * Constraints:
 * 1 <= nums.length <= 104
 * -104 < nums[i], target < 104
 * All the integers in nums are unique.
 * nums is sorted in ascending order.
 *
 * */
class Solution {
    public boolean checkIfAscendingOrder(int[] nums) {
        if (nums.length == 1) return true;

        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < pre)
                return false;
        }
        return true;
    }
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        if (nums.length == 1 )
            return (nums[0] == target) ? 0 : -1;

        // checkAscendingOrder
        if (!checkIfAscendingOrder(nums))
            return -2;

        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return -1;
    }
}

class Test {

    public static void main(String[] args) {

        Solution s = new Solution();

        // test 1 null / [] / [1], target = 3, Output: -1
        int[] i1 = new int[] {1};
        int t1 = 3;
        System.out.println(s.search(i1, t1));
        System.out.println(s.search(null, t1));
        System.out.println(s.search(new int[]{}, t1));

        // test 2 nums = [-1,0,3,5,9,12], target = 9, Output: 4
        int[] i2 = new int[] {-1,0,3,5,9,12};
        int t2 = 9;
        System.out.println(s.search(i2, t2));

        // test 3 nums = [-1,0,3,5,9,12], target = 2, Output: -1
        int[] i3 = new int[] {-1,0,3,5,9,12};
        int t3 = 2;
        System.out.println(s.search(i3, t3));

        // test 4 nums has duplicated elements, JUST TEST,
        // test 4 nums = [-1,0,0,0,9,12], target = 2, Output: 1
        int[] i4 = new int[] {-1,0,0,0,9,12};
        int t4 = 0;
        System.out.println(s.search(i4, t4));

        // test 5 for not ascend nums, Output: 2
        int[] i5 = new int[] {10,9,8,7,6,5,4,3,2,1};
        int t5 = 5;
        System.out.println(s.search(i5, t5));

    }
}