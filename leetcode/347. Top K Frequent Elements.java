import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/*347. Top K Frequent Elements
 * must have time cost less than O(nlogn)
 * O(nlogk) < O(nlogn) for k is much less than n in average
 * */
class Solution {
    // O(nlogk), get rid of HashMap, use most of memory
    static final int INDEX_OFFSET = 10000;
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 1) return nums;
        int[] ans = new int[k];
        int[] countMap = new int[20001];    // -10^4 <= nums[i] <= 10^4
        // 构建最小堆，堆顶是最小值
        PriorityQueue<Integer> pq =
                new PriorityQueue<>(k, Comparator.comparingInt(o -> countMap[o]));
        // O(n)
        for (int num : nums) {
            // nums[i] = -10000, countMaps index is 0(-10000+INDEX_OFFSET)
            countMap[num + INDEX_OFFSET]++;
        }
        // O(nlogk)
        for (int num = 0; num < 20001; num++) {
            int count = countMap[num];
            if (k > 0) {
                pq.add(num);      // O(logk)
                k--;
            } else {
                int headCount = countMap[pq.element()];
                if (count > headCount) {
                    pq.remove();
                    pq.add(num);
                }
            }
        }
        while (!pq.isEmpty()) {
            ans[pq.size()-1] = pq.remove() - INDEX_OFFSET;
        }
        return ans;
    }
    // O(nlogk)
    public int[] topKFrequent1(int[] nums, int k) {
        if (nums.length == 1) return nums;
        int[] ans = new int[k];
        HashMap<Integer, Integer> countMap = new HashMap<>();
        // 构建最小堆，堆顶是最小值
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, Comparator.comparingInt(countMap::get));
        // O(n)
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        // O(nlogk)
        for (Integer num : countMap.keySet()) {
            int count = countMap.get(num);
            if (k > 0) {
                pq.add(num);      // O(logk)
                k--;
            } else {
                int headCount = countMap.get(pq.element());
                if (count > headCount) {
                    pq.remove();
                    pq.add(num);
                }
            }
        }
        while (!pq.isEmpty()) {
            ans[pq.size()-1] = pq.remove();
        }
        return ans;
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();
        // test1: [1], k = 1, -> [1]
        System.out.println(Arrays.toString(s.topKFrequent(new int[]{1}, 1)));
        // test2: [1,1,2], k = 1, -> [1]
        System.out.println(Arrays.toString(s.topKFrequent(new int[]{1,1,2}, 1)));
        // test3: nums = [1,1,1,2,2,3], k = 2, -> [1,2]
        System.out.println(Arrays.toString(s.topKFrequent(new int[]{1,1,1,2,2,3}, 2)));
        // test4: [4,1,-1,2,-1,2,3], k = 2 -> [-1,2]
        System.out.println(Arrays.toString(s.topKFrequent(new int[]{4,1,-1,2,-1,2,3}, 2)));
    }
}