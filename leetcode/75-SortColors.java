/*
 * 75. Sort Colors
 * Given an array nums with n objects colored red, white, or blue,
 * sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * You must solve this problem without using the library's sort function.
 * 给定一个数组，包含n个旗子，旗子有三种颜色：红、白、蓝
 * 实现对原数组的排序方法，使得相同颜色的旗子排在一起，且按照红、白、蓝顺序排
 * 不能使用库排序函数
 *
 * Example 1:
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * Example 2:
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is either 0, 1, or 2.
 *
 * Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 * */

import java.util.Arrays;

/* 75
 * 解法1：快速排序，排序后的结果即满足要求。但不是 one-pass 解法
 * 解法2：荷兰国旗问题，双针法解决，关键点在于将0换到高位时，从高位换过来的数仍可能是个较大的数，此时遍历次数i不能+1
 * */

class Solution {
    void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    // 荷兰国旗问题
    void merge(int[] nums, int low, int high) {
        int i = low;
        while (i <= high) {
            if (nums[i] == 0)
                swap(nums, i++, low++);
            else if (nums[i] == 2)
                swap(nums, i, high--);      // critical, may swap a 1 to i, so i don`t increase
            else
                i++;
        }
    }
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) return;

//        quickSort(nums, 0, nums.length - 1);
        merge(nums, 0, nums.length - 1);
    }

    int getRandomPivot(int[] nums, int low, int high) {
        int p1 = nums[low];
        int p2 = nums[(low + high) >> 1];
        int p3 = nums[high];
        int p = Math.min(Math.max(p1, p2), Math.max(p2, p3));
        int pIdx;
        if (p == nums[low]) pIdx = low;
        else if (p == nums[high]) pIdx = high;
        else pIdx = (low + high) >> 1;
        return pIdx;
    }

    int partition(int[] nums, int low, int high) {
        // select 3 pivot, then select the middle from them
        int pivot = getRandomPivot(nums, low, high);
        int temp = nums[pivot];
        nums[pivot] = nums[low];
        nums[low] = temp;

        temp = nums[low];
        while (low < high) {
            while (low < high && nums[high] > temp) --high;
            nums[low] = nums[high];
            while (low < high && nums[low] <= temp) ++low;
            nums[high] = nums[low];
        }
        nums[low] = temp;
        return low;
    }

    public void quickSort(int[] nums, int low, int high) {
        if (low < high) {
            int idx = partition(nums, low, high);

            quickSort(nums, low, idx - 1);
            quickSort(nums, idx + 1, high);
        }
    }
}

class Test {
    public static void main(String[] args) {

        Solution s = new Solution();
        // test 1 []
//        int[] i1 = null;
//        s.sortColors(i1);
//        System.out.println(Arrays.toString(i1));
//        // test 2 []
//        int[] i2 = new int[]{};
//        s.sortColors(i2);
//        System.out.println(Arrays.toString(i2));
//        // test 3 [1]
//        int[] i3 = new int[]{1};
//        s.sortColors(i3);
//        System.out.println(Arrays.toString(i3));
//        // test 4 [2,1,0]
//        int[] i4 = new int[]{2,1,0};
//        s.sortColors(i4);
//        System.out.println(Arrays.toString(i4));
//        // test 5 [2,1,0]
//        int[] i5 = new int[]{2,0,2,1,1,0};
//        s.sortColors(i5);
//        System.out.println(Arrays.toString(i5));
//        // test 6 [2,1,0]
        int[] i6 = new int[]{0,2,1,2,0,2,0,1};
        s.sortColors(i6);
        System.out.println(Arrays.toString(i6));

    }
}