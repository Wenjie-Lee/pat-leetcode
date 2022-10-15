import java.util.Arrays;

/**
 * 88. Merge Sorted Array
 * You are given two integer arrays nums1 and nums2, sorted in <bi>non-decreasing order</b>,
 * and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.<p>
 *
 * <b>Follow up</b>: Can you come up with an algorithm that runs in O(m + n) time?<p>
 *
 * 解法：
 * 类似于归并排序的合并操作
 * */
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;

        int i = 0, j = 0, k = 0;
        // nums1 are empty, just insert nums2
        // or all elements in nums1 are smaller than nums2
        if (m == 0 || nums1[m-1] <= nums2[0]) k = m;
        else if (nums1[m-1] > nums2[0]) {
            int[] aux = Arrays.copyOf(nums1, m);
            while (i < m && j < n) {
                if (aux[i] < nums2[j])  nums1[k++] = aux[i++];
                else                    nums1[k++] = nums2[j++];
            }
            while (i < m) nums1[k++] = aux[i++];
        }
        while (j < n) nums1[k++] = nums2[j++];
        // assert non-decreasing sorted
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();
        // test0: nums1=[0], nums2=[1] -> nums1=[1]
        int[] t0 = new int[]{0};
        s.merge(t0, 0, new int[]{1}, 1);
        System.out.println(Arrays.toString(t0));
        // test1: nums1=[1,2,3,0,0,0], nums2=[4,5,6] -> nums1=[1,2,3,4,5,6]
        int[] t1 = new int[]{1,2,3,0,0,0};
        s.merge(t1, 3, new int[]{4,5,6}, 3);
        System.out.println(Arrays.toString(t1));
        // test2: nums1=[4,5,6,0,0,0], nums2=[1,2,3] -> nums1=[1,2,3,4,5,6]
        int[] t2 = new int[]{4,5,6,0,0,0};
        s.merge(t2, 3, new int[]{1,2,3}, 3);
        System.out.println(Arrays.toString(t2));
        // test3: nums1=[1,3,5,0,0,0], nums2=[2,4,6] -> nums1=[1,2,3,4,5,6]
        int[] t3 = new int[]{1,3,5,0,0,0};
        s.merge(t3, 3, new int[]{2,4,6}, 3);
        System.out.println(Arrays.toString(t3));
        // test4: nums1=[1,2,3,0,0,0], nums2=[2,5,6] -> nums1=[1,2,2,3,5,6]
        int[] t4 = new int[]{1,2,3,0,0,0};
        s.merge(t4, 3, new int[]{2,5,6}, 3);
        System.out.println(Arrays.toString(t4));
    }
}