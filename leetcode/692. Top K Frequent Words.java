import java.util.*;
import java.util.stream.Collectors;

/**
 * 692. Top K Frequent Words
 * Given an array of strings words and an integer k, return the <b>k most frequent strings</b>.<p>
 *
 * Return the answer sorted by the <b>frequency</b> from highest to lowest.
 * Sort the words with the same frequency by their lexicographical order.<p>
 *
 * 解法：<p>
 * 优先队列 O(nlogn) time cost, O(n) extra space<p>
 * 相比小根堆只维护K大小，优先队列最坏情况的长度为n，均值为n/2<p>
 * 当大部分情况k < n/2时，优先队列方法更快
 * */
class Solution {
    // 优先队列 O(nlogn) time cost, O(n) extra space
    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || words.length == 0) return null;
        // O(n) extra space
        Map<String, Integer> mapCount = new HashMap<>();
        List<String> ans = new ArrayList<>(k);
        // O(n) time cost
        for (String word : words)
            mapCount.put(word, mapCount.getOrDefault(word, 1) + 1);
        // 优先队列, O(n) time cost
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (o1, o2) -> {
                    if ((int) o2.getValue() == o1.getValue())
                        return o1.getKey().compareTo(o2.getKey());
                    return Integer.compare(o2.getValue(), o1.getValue());
                });
        // O(nlogk) time cost
        for (Map.Entry<String, Integer> e : mapCount.entrySet())
            pq.offer(e);
        // O(n) time cost
        while (k-- > 0) ans.add(pq.poll().getKey());
        return ans;
    }
    // 小根堆 O(nlogk) time cost, O(n) extra space
    public List<String> topKFrequent2(String[] words, int k) {
        if (words == null || words.length == 0) return null;
        // O(n) extra space
        Map<String, Integer> mapCount = new HashMap<>();
        String[] ans = new String[k];
        Comparator<Map.Entry<String, Integer>> comparator = (o1, o2) -> {
            if ((int) o1.getValue() == o2.getValue())
                return -o1.getKey().compareTo(o2.getKey());
            return Integer.compare(o1.getValue(), o2.getValue());
        };
        // O(n) time cost
        for (String s : words)
            mapCount.put(s, mapCount.getOrDefault(s, 1) + 1);
        // 小根堆, O(n) time cost
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(k, comparator);
        // O(nlogk) time cost
        for (Map.Entry<String, Integer> e : mapCount.entrySet()) {
            if (pq.size() < k)
                pq.offer(e);
            else {
                if (comparator.compare(e, pq.peek()) > 0) {
                    pq.poll();
                    pq.offer(e);
                }
            }
        }
        // O(n) time cost
        while (k-- > 0) ans[k] = pq.poll().getKey();
        return Arrays.stream(ans).collect(Collectors.toList());
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();
        // test1: words=["i","love","leetcode","i","love","coding"], k=2
        // -> ["i","love"]
        String[] s1 = new String[]{"i","love","leetcode","i","love","coding"};
        System.out.println(s.topKFrequent(s1, 2));
        // test2: words=["the","day","is","sunny","the","the","the","sunny","is","is"], k=4
        // -> ["the","is","sunny","day"]
        String[] s2 = new String[]{"the","day","is","sunny","the","the","the","sunny","is","is"};
        System.out.println(s.topKFrequent(s2, 4));
        // test3: words=["i","love","leetcode","i","love","coding"], k=1
        // -> ["a"]
        String[] s3 = new String[]{"i","love","leetcode","i","love","coding"};
        System.out.println(s.topKFrequent(s3, 1));
    }
}