import java.util.*;

/*
15. 3Sum

Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k,
and nums[i] + nums[j] + nums[k] == 0.Notice that the solution set must not contain duplicate triplets.

Example 1:
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation:
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.

Example 2:
Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.

Example 3:
Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.

Constraints:
3 <= nums.length <= 3000
-10^5 <= nums[i] <= 10^5
* */

/*
 * 双针法
 * 外部一个循环k，内部嵌套一个双针法
 * 不做剪枝，直接对所有结果进行排序、踢出重复，样例全过但是超时
 * 做剪枝，当找到一个解时，分别对三个指针i,j,k向后移直到指向一个与解不同的位置
 * */

class Solution {

//    Comparator<List<Integer>> comparator = new Comparator<List<Integer>>() {
//        @Override
//        public int compare(List<Integer> o1, List<Integer> o2) {
//            for (int i = 0; i < o1.size(); i++) {
//                if (o1.get(i).compareTo(o2.get(i)) > 0)
//                    return 1;
//                else if (o1.get(i).compareTo(o2.get(i)) < 0)
//                    return -1;
//            }
//            return 0;
//        }
//    };

    public List<List<Integer>> threeSum(int[] nums) {
        // guarantee the length of nums >= 3
        Arrays.sort(nums);  // make sure the nums array is ordered
        List<List<Integer>> list = new ArrayList<>();

        for (int k = 0; k < nums.length; k++) {
            int i = k + 1, j = nums.length - 1;
            while (i < j) {
                if (i == k) {
                    i++;
                    continue;
                }
                if (j == k) {
                    j--;
                    continue;
                }
                int sum = nums[i] + nums[k] + nums[j];
                if (sum > 0) {
                    j -= 1;
                } else if (sum < 0) {
                    i += 1;
                } else {
                    List<Integer> l = new ArrayList<>();
                    l.add(nums[k]);
                    l.add(nums[i]);
                    l.add(nums[j]);
                    list.add(l);
                    // skip the
                    while (i < j && nums[i] == nums[i+1]) i++;
                    while (i < j && nums[j] == nums[j-1]) j--;
                    i++;
                    j--;

                }
            }
            while (k < nums.length-1 && nums[k] == nums[k+1]) k++;
        }
        return list;
//        if (list.isEmpty())
//            return list;
//        list.sort(comparator);
//        List<List<Integer>> list2 = new ArrayList<>();
//        for (List<Integer> it : list) {
//            if (list2.isEmpty())
//                list2.add(it);
//            else if (comparator.compare(list2.get(list2.size()-1), it) != 0) {
//                list2.add(it);
//            }
//        }
//        return list2;
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 题目确保了输入数组length >= 3

        // [0,1,1] 无解样例
        int[] i2 = new int[]{0,1,1};
        System.out.println(solution.threeSum(i2));
        // [0,0,0] 单个解
        int[] i3 = new int[]{0,0,0};
        System.out.println(solution.threeSum(i3));
        // [-1,0,1,2,-1,-4] 有重复解
        int[] i1 = new int[]{-1,0,1,2,-1,-4};
        System.out.println(solution.threeSum(i1));
        // [0,0,1,1,2,2] 有重复解
        int[] i4 = new int[]{-1,-1,0,0,1,1};
        System.out.println(solution.threeSum(i4));
        // [-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6]
        int[] i5 = new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        System.out.println(solution.threeSum(i5));
    }
}