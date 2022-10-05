
/*215. Kth Largest Element in an Array
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * You must solve it in O(n) time complexity.
 *
 * Constraints:
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * */



/*
 * 解法：常规排序不满足O(n)时间复杂度，采用快速排序的partition方法
 * 分割出小于nums[k]和大于nums[k]
 * */

import java.util.Arrays;
import java.util.Random;

class Solution {
    private int partition(int[] nums, int low, int high) {

        int temp = nums[low];
        while (low < high) {
            while (low < high && nums[high] > temp) high--;
            nums[low] = nums[high];
            while (low < high && nums[low] <= temp) low++;
            nums[high] = nums[low];
        }
        nums[low] = temp;
        return low;
    }

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < 1) return -1;
//        if (k > nums.length) return -1;
//
        k = nums.length - k;

        int start = 0, end = nums.length - 1;
        while (start < end) {
            int pivot = partition(nums, start, end);
            if (pivot == k)
                return nums[pivot];
            else if (pivot < k)
                start = pivot + 1;
//                pivot = partition(nums, pivot + 1, nums.length - 1);
            else
                end = pivot - 1;
//                pivot = partition(nums, 0, pivot - 1);
        }
        return nums[start];
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();

        //test 1 nums = null | []
        System.out.printf("test1: %d in %s\n", s.findKthLargest(null, 1), null);
        int[] i1 = new int[] {};
        System.out.printf("test1: %d in %s\n", s.findKthLargest(i1, 1), Arrays.toString(i1));

        // test 2 nums = [1], k = 1, Output: 1
        int[] i2 = new int[] {1};
        System.out.printf("test2: %d in %s\n", s.findKthLargest(i2, 1), Arrays.toString(i2));

        // test 3 nums = [3,2,1,5,6,4], k = 2, Output: 5
        int[] i3 = new int[] {3,2,1,5,6,4};
        System.out.printf("test3: %d in %s\n", s.findKthLargest(i3, 2), Arrays.toString(i3));

        // test 4 nums = [3,2,3,1,2,4,5,5,6], k = 4, Output: 4
        int[] i4 = new int[] {3,2,3,1,2,4,5,5,6};
        System.out.printf("test4: %d in %s\n", s.findKthLargest(i4, 4), Arrays.toString(i4));

        // test 5 nums = [6,6,6,6,6,6,6,1], k = 4, Output: 4
        int[] i5 = new int[] {6,6,6,6,6,6,6,1};
        System.out.printf("test5: %d in %s\n", s.findKthLargest(i5, 4), Arrays.toString(i5));

        // test 6 nums = [7,6,5,4,3,2,1], k = 5, Output: 4
        int[] i6 = new int[] {7,6,5,4,3,2,1};
        System.out.printf("test5: %d in %s\n", s.findKthLargest(i6, 5), Arrays.toString(i6));
    }
}