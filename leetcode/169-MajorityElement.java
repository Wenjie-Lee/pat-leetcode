import java.util.Random;

/*169
Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times.
You may assume that the majority element always exists in the array.
给定一个长度为n的数组，返回占多数的元素，即占据数组一半及以上的元素
Example 1:
Input: nums = [3,2,3]
Output: 3

Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2

Constraints:
n == nums.length
1 <= n <= 5 * 10^4
-10^9 <= nums[i] <= 10^9

Follow-up: Could you solve the problem in linear time and in O(1) space?
* */
class Solution {
    int getRandomPivot(int[] nums, int low, int high) {
        int p1 = nums[low];
        int p2 = nums[(low + high) >> 1];
        int p3 = nums[high];
        int p = Math.min(Math.max(p1, p2), Math.max(p1, p3));
        int pIdx;
        if (p == nums[low]) pIdx = low;
        else if (p == nums[high]) pIdx = high;
        else pIdx = (low + high) >> 2;
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
            while (low < high && nums[high] >= temp) --high;
            nums[low] = nums[high];
            while (low < high && nums[low] <= temp) ++low;
            nums[high] = nums[low];
        }
        nums[low] = temp;
        return low;
    }

    public int majorityElement(int[] nums) {
        if (nums.length == 1) return nums[0];

        int mid = nums.length >> 1;
        int low = 0, high = nums.length - 1;
        int idx = partition(nums, low, high);
        while (idx != mid) {
            if (idx > mid) {
                high = idx - 1;
                idx = partition(nums, low, high);
            }
            else {
                low = idx + 1;
                idx = partition(nums, low, high);
            }
        }
        // a checkIfMoreThanHalf is need if not assume
        // that the majority element always exists in the array
        return nums[mid];
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // [1]
        int[] i1 = new int[]{1};
        System.out.println(solution.majorityElement(i1));
        // [3,2,3]
        int[] i2 = new int[]{3,2,3};
        System.out.println(solution.majorityElement(i2));
        // [2,2,1,1,1,2,2]
        int[] i3 = new int[]{2,2,1,1,1,2,2};
        System.out.println(solution.majorityElement(i3));
        // []
        int[] i4 = new int[]{32,41,21,29,7,53,97,76,71,53,53,
                53,72,53,53,14,22,53,67,53,53,53,54,98,53,46,
                53,53,62,53,76,20,60,53,31,53,53,53,95,27,53,
                53,53,53,36,59,40,53,53,64,53,53,53,21,53,51,
                53,53,2,53,53,53,53,53,50,53,53,53,23,88,3,
                53,61,19,53,68,53,35,42,53,53,48,34,54,53,75,
                53,53,50,44,92,41,71,53,53,82,53,53,14,53};
        System.out.println(solution.majorityElement(i4));

        int[] i5 = new int[]{6,6,6,7,7};
        System.out.println(solution.majorityElement(i5));
    }
}