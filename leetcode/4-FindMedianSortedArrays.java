
/*4. Median of Two Sorted Arrays
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 *
 * Constraints:
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 * */

/* （没说升序降序，但是输入降序序列报错，所以按都是升序处理）
给定两个有序的数组,返回两个数组总的中位数(注意，中位数不一定在数组内，中位数如果有2个，则返回2个的平均)
*  要求：时间复杂度O(log (m+n))
*
* 时间复杂度O(log (m+n))，则不能有合并两个数组的操作（合并操作为O（m+n））
* 解法：二分法+快速排序切分方法
* 在两个数组中找到一个数，这个数切割两个数组，使得左右两部分长度相等（即这个数正好在左右部分的中间）
*
* */
class Solution {
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        // guarantee that m+1 >= 1, means at least one nums are not empty
        if (nums1.length < 1)
            return  nums2[(nums2.length-1)/2]*0.5 + nums2[nums2.length/2]*0.5;
        if (nums2.length < 1)
            return  nums1[(nums1.length-1)/2]*0.5 + nums1[nums1.length/2]*0.5;

        // choose shorter to binary search
        if (nums1.length > nums2.length)
            return findMedianSortedArrays(nums2, nums1);

        int m = nums1.length, n = nums2.length;
        int low = 0, high = m;
        int halfLength = (m + n) / 2;
        while (low < high) {
            // nums1[0]~nums1[left] + nums2[0]~nums[right-1] are smaller part
            // left + (right-1) should equal to halfLength
            int left = (low + high) / 2;
            int right = halfLength - left;
            if (nums1[left] < nums2[right - 1])
                low = left + 1;
            else
                high = left;
        }
        int first = low;
        int second = halfLength - low;

        int shorterLeft = first == 0 ? Integer.MIN_VALUE : nums1[first - 1];
        int shorterRight = first == m ? Integer.MAX_VALUE : nums1[first];

        int longerLeft = second == 0 ? Integer.MIN_VALUE : nums2[second - 1];
        int longerRight = second == n ? Integer.MAX_VALUE : nums2[second];

        double med1 = Math.min(shorterRight, longerRight);
        double med2 = Math.max(shorterLeft, longerLeft);
        if ((m + n) % 2 == 1)
            return med1;
        else
            return med1 * 0.5 + med2 * 0.5;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // guarantee that m+1 >= 1, means at least one nums are not empty
        if (nums1.length < 1)
            return  nums2[(nums2.length-1)/2]*0.5 + nums2[nums2.length/2]*0.5;
        if (nums2.length < 1)
            return  nums1[(nums1.length-1)/2]*0.5 + nums1[nums1.length/2]*0.5;

        int idx1 = 0, idx2 = 0;
        int m = nums1.length, n = nums2.length;
        int len = n + m;
        int med1 = 0, med2 = 0;

        for (int i = 0; i <= (len / 2); i++) {
            med1 =  med2;
            if (idx1 == nums1.length)
                med2 = nums2[idx2++];
            else if (idx2 == nums2.length)
                med2 = nums1[idx1++];
            else if (nums1[idx1] < nums2[idx2])
                med2 = nums1[idx1++];
            else
                med2 = nums2[idx2++];     // med2 is the fist median
        }

        if (len % 2 == 1) {
            return med2 * 1.0d;
        } else {
            return ((double) (med1 + med2)) / 2;
        }
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();

        // test 1 两个空数组，输入保证了不会出现

        // test 2 其中一个数组空 2.0
//        int[] i2 = new int[]{1,3}, i22 = new int[] {};
//        System.out.println(s.findMedianSortedArrays(i2, i22));

        // test 3 常规    2.0
        int[] i3 = new int[]{10001}, i32 = new int[] {10000};
        System.out.println(s.findMedianSortedArrays(i3, i32));

        // test 4 中位数有2个的情况     3.5
        int[] i4 = new int[]{1,3,5}, i42 = new int[] {2,4,6};
        System.out.println(s.findMedianSortedArrays(i4, i42));

        // test 5 中位数有2个的情况 5.0
        int[] i5 = new int[]{1,2,3,4,5,6}, i45 = new int[] {7,8,9};
        System.out.println(s.findMedianSortedArrays(i5, i45));
    }
}