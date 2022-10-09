import java.util.*;

/* 350. Intersection of Two Arrays II
 *
 *
 * */
class Solution {
    // O(nlogn)
    // assume if input nums1 & nums2 are sorted
    public int[] intersect(int[] nums1, int[] nums2) {
        // make nums1 are always the shorter one
        if (nums2.length < nums1.length) return intersect(nums2, nums1);
        // res cannot be longer than the shortest array
        int[] res = new int[nums1.length];
        int i = 0, j = 0, resIdx = 0;
        Arrays.sort(nums1); // O(mlogm)
        Arrays.sort(nums2); // O(nlogn)
        // O(m + n)
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) i++;
            else if (nums1[i] > nums2[j]) j++;
            else {
                res[resIdx++] = nums1[i];
                i++; j++;
            }
        }
        return Arrays.copyOfRange(res, 0, resIdx);
    }
    // O(m + n)
    // c++ style
    public int[] intersect2(int[] nums1, int[] nums2) {
        int[] map = new int[1001];
        int[] res = new int[1001];
        int idx = 0;
        for (int j : nums1) // O(n)
            map[j]++;
        for (int j : nums2) { // O(m)
            if (map[j] > 0) {
                map[j]--;
                res[idx++] = j;
            }
        }
        return Arrays.copyOfRange(res, 0, idx);
    }
    // O(m + n)
    public int[] intersect3(int[] nums1, int[] nums2) {
        if (nums2.length < nums1.length) return intersect(nums2, nums1);

        HashMap<Integer, Integer> count = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            int j = nums1[i];
            if (!count.containsKey(j))
                count.put(j, 1);
            else
                count.put(j, count.get(j) + 1);
        }
        for (int i = 0; i < nums2.length; i++) {
            int j = nums2[i];
            if (count.containsKey(j) && count.get(j) > 0) {
                list.add(j);
                count.put(j, count.get(j) - 1);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            res[i] = list.get(i);
        return res;
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();

        // test1: nums1 = [1,2,2,1], nums2 = [2,2] -> [2,2]
        System.out.println(Arrays.toString(s.intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2})));
        // test2: nums1 = [4,9,5], nums2 = [9,4,9,8,4] -> [4,9] or [9,4]
        System.out.println(Arrays.toString(s.intersect(new int[]{4,9,5}, new int[]{9,4,9,8,4})));
        // test3: nums1 = [1,2,3], nums2 = [1,1,1,2,2,2,3,3,3] -> [1,2,3]
        System.out.println(Arrays.toString(s.intersect(new int[]{1,1,1,2,2,2,3,3,3}, new int[]{1,2,3})));
        // test4: nums1 = [1], nums2 = [2,3,4] -> []
        System.out.println(Arrays.toString(s.intersect(new int[]{1}, new int[]{2,3,4})));
    }
}