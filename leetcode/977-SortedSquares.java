/*977. Squares of a Sorted Array
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each
 * number sorted in non-decreasing order.
 * 给定一个非降序整数数组，将所有元素取平方，后返回非降序数组
 *
 * Example 1:
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 *
 * Example 2:
 * Input: nums = [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]

 * Constraints:
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums is sorted in non-decreasing order.

 * Follow up: Squaring each element and sorting the new array is very trivial,
 * could you find an O(n) solution using a different approach?
 *
 * */

import java.util.Arrays;


/*
 * 充分利用有序的特征，数组两端的元素求平方后一定比数组中间的要大，所以先插入数组两端的平方元素。
 * 使用两个指针由两端向中间遍历，将平方后元素较大的填入新数组末尾
 * */
class Solution {
    public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length < 1) return nums;

        int i = 0, j = nums.length - 1, k = nums.length - 1;
        int[] out = new int[nums.length];
        while (i <= j) {
            if (Math.abs(nums[i]) > Math.abs(nums[j])) {
                out[k--] = nums[i] * nums[i++];
            }
            else {
                out[k--] = nums[j] * nums[j--];
            }
        }
        return out;
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();

        // test 1 nums = [-4], Output: [16]
        int[] i1 = new int[] {-4};
        System.out.println(Arrays.toString(s.sortedSquares(i1)));
        // test 2 nums = [-4,1], Output: [1,16]
        int[] i2 = new int[] {-4,1};
        System.out.println(Arrays.toString(s.sortedSquares(i2)));
        // test 3 nums = [-4,-1,0,3,10], Output: [0,1,9,16,100]
        int[] i3 = new int[] {-4,-1,0,3,10};
        System.out.println(Arrays.toString(s.sortedSquares(i3)));
        // test 4 nums = [-7,-3,2,3,11], Output: [4,9,9,49,121]
        int[] i4 = new int[] {-7,-3,2,3,11};
        System.out.println(Arrays.toString(s.sortedSquares(i4)));
        // test 5 nums = [-7,-7,-7,-7,-7], Output: [49,49,49,49,49]
        int[] i5 = new int[] {-7,-7,-7,-7,-7};
        System.out.println(Arrays.toString(s.sortedSquares(i5)));
    }
}