import java.util.PriorityQueue;

/**295. Find Median from Data Stream<p>
 * The median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value and the median is the mean of the two middle values.
 * For example:
 * <pre>
 *     {@code for arr = [2,3,4], the median is 3.}
 *     {@code for arr = [2,3], the median is (2 + 3) / 2 = 2.5.}
 * </pre>
 * Implement the MedianFinder class:<p>
 * * MedianFinder() initializes the MedianFinder object.<p>
 * * void addNum(int num) adds the integer num from the data stream to the data structure.<p>
 * * double findMedian() returns the median of all elements so far.
 * Answers within 10^-5 of the actual answer will be accepted.<p>
 *
 * 解法：
 * 使用一个大根堆维护前半部分，一个小根堆维护后半部分<p>
 *
 * Follow up:<p>
 * <pre>
 * 1. If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?<p>
 * 维护一个长度为100的数组，用于记录0~100的出现次数；遍历数组来寻找中位数
 * </pre>
 * <pre>
 * 2. If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?<p>
 * 同样维护一个长度为100的数组，额外再使用两个计数器保存两个方向上1%的数的个数(因为中位数只会出现再[0, 100])；同样通过遍历数组来寻找中位数，只不过开始数要加上左边计数器数值
 * </pre>
 * */


class MedianFinder {
    private PriorityQueue<Integer> pq1, pq2;
    public MedianFinder() {
        pq1 = new PriorityQueue<>((o1, o2) -> o2 - o1);
        pq2 = new PriorityQueue<>();    // (o1, o2) -> o1 - o2
    }

    public void addNum(int num) {
        // 添加新元素
        // if (even) {
        //     pq2.offer(num);
        //     pq1.offer(pq2.poll());
        // } else {
        //     pq1.offer(num);
        //     pq2.offer(pq1.poll());
        // }
        // even = !even;
        if (pq1.isEmpty() || num <= pq1.peek())
            pq1.offer(num);
        else
            pq2.offer(num);

        if (pq2.size() > pq1.size())
            pq1.offer(pq2.poll());
        else if (pq1.size() > pq2.size() + 1)
            pq2.offer(pq1.poll());
    }

    public double findMedian() {
//        if (even)
//            return (pq1.peek() + pq2.peek()) / 2.0;
//        else
//            return pq1.peek();
        if (pq1.size() == pq2.size())
            return (pq1.peek() + pq2.peek()) / 2.0;
        else
            return pq1.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

class Solution {
    public static void main(String[] args) {
        // test1: 1,2,3 -> 2.0
        MedianFinder m1 = new MedianFinder();
        m1.addNum(1); m1.addNum(2);
        System.out.println(m1.findMedian());    // 1.5
        m1.addNum(3);
        System.out.println(m1.findMedian());    // 2.0
        m1.addNum(3);
        System.out.println(m1.findMedian());    // 2.5
        m1.addNum(3); m1.addNum(3); m1.addNum(3);
        System.out.println(m1.findMedian());    // 3.0
        // test2: 1 -> 1
        MedianFinder m2 = new MedianFinder();
        m2.addNum(1);
        System.out.println(m2.findMedian());    // 1.0
        // test3: 1 -> 1
        MedianFinder m3 = new MedianFinder();
        m3.addNum(1); m3.addNum(2); m3.addNum(-1); m3.addNum(-2);
        System.out.println(m3.findMedian());    // 0.0
    }
}