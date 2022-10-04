import java.util.Arrays;

/**
 * 189. Rotate Array
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * <p>
 * Example 2:
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 * <p>
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^5
 * <p>
 * Follow up:
 * Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 */


class Solution {

    public void rotate(int[] nums, int k) {
        rotateReverse(nums, k);
    }

    public void rotateReverse(int[] nums, int k) {
        // guarantee 1 <= nums.length <= 10^5
        if (nums == null || nums.length < 2) return;

        k %= nums.length;

        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    public void reverse(int[] nums, int low, int high) {
        while (low < high) {
            int temp = nums[low];
            nums[low++] = nums[high];
            nums[high--] = temp;
        }
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();

        // test 1, nums = [1], k = 0
        int[] i1 = new int[] {1};
        s.rotate(i1, 0);
        System.out.println(Arrays.toString(i1));
        // test 2, nums = [1,2,3,4,5,6,7], k = 3, Output: [5,6,7,1,2,3,4]
        int[] i2 = new int[] {1,2,3,4,5,6,7};
        s.rotate(i2, 3);
        System.out.println(Arrays.toString(i2));
        // test 3, nums = [-1,-100,3,99], k = 2, Output: [3,99,-1,-100]
        int[] i3 = new int[] {-1,-100,3,99};
        s.rotate(i3, 2);
        System.out.println(Arrays.toString(i3));
        // test 4, nums = [-1,-100,3,99], k = 0, Output: [-1,-100,3,99]
        int[] i4 = new int[] {-1,-100,3,99};
        s.rotate(i4, 0);
        System.out.println(Arrays.toString(i4));
        // test 5, nums = [-1,-100,3,99], k = 8, Output: [-1,-100,3,99]
        int[] i5 = new int[] {-1,-100,3,99};
        s.rotate(i5, 8);
        System.out.println(Arrays.toString(i5));
        // test 6, nums = [1,2,3,4,5,6,7], k = 13, Output: [5,6,7,1,2,3,4]
        int[] i6 = new int[] {1,2,3,4,5,6,7};
        s.rotate(i6, 1);
        System.out.println(Arrays.toString(i6));

    }
}