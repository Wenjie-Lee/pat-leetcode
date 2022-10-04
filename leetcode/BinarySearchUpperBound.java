
/*
 * 35. Search Insert Position
 * Given a sorted array of distinct integers and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * You must write an algorithm with O(log n) runtime complexity.
 * 给定一个升序、无重复整数数组，和一个目标值，返回目标值在数组中的位置，
 * 如果此值不在数组中，则返回它应该在的位置
 * 确保O(log n)时间复杂度
 *
 * Example 1:
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 *
 * Example 3:
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 *
 * Constraints:
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums contains distinct values sorted in ascending order.
 * -10^4 <= target <= 10^4
 * */



class Solution {
    // binary search upper bound
    // target为目标数，先查找target，若找到，则返回位置
    // 否则，函数返回第一个大于targe的数的位置（即它要插入的位置）
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0 )return 0;

        int low = 0, high = nums.length;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                low = mid + 1;
            else {
                high = mid;
            }
        }
        return low;
    }
}

class Test {

    public static void main(String[] args) {
        Solution s = new Solution();

        // test 1 len < 1, Output: 0
        System.out.println(s.searchInsert(new int[]{}, 1));
        // test 2 nums = [1], target = 1 / 2, Output: 0, 1
        System.out.println(s.searchInsert(new int[]{1}, 1));
        System.out.println(s.searchInsert(new int[]{1}, 2));
        // test 3 nums = [1,3,5,6], target = 5, Output: 2
        System.out.println(s.searchInsert(new int[]{1,3,5,6}, 5));
        // test 4 nums = [1,3,5,6], target = 7, Output: 4
        System.out.println(s.searchInsert(new int[]{1,3,5,6}, 7));
        // test 5 nums = [1,2,3,4,5,7], target = 6, Output: 5
        System.out.println(s.searchInsert(new int[]{1,2,3,4,5,7}, 6));
    }
}