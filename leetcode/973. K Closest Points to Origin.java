/*973. K Closest Points to Origin
 *
 * */

/*
 * 解法：
 * 1. 若点的个数不太多（不超过内存限制），则可用Partition分割前K-1个和后len-K个，O(n)
 * 2. 还可以使用优先队列维护前K个，最坏情况下O(nlogk)
 * 3. 内存要求严格时，可以将点数组保存为文件，一次读取一部分
 * 4. 可以利用分布式思想，多台机器各做一部分，再在一台上进行汇总
 * */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

class Point {
    private static final Point ORIGIN;
    private final int[] point;
    private final double distance;

    public Point(int[] point) {
        this.point = point.clone();
        distance = euclideanDistance(point);
    }
    static {
        ORIGIN = new Point(new int[]{0, 0});
    }

    public int[] getPoint() {
        return point;
    }

    public double getDistance() {
        return distance;
    }

    static double euclideanDistance(int[] point) {
        // Math.sqrt() is redundant for distance compare
        return Math.pow(point[0], 2) + Math.pow(point[1], 2);
    }
    // static factory
    public static Point Origin() {
        return ORIGIN;
    }
    public int compareTo(Point p2) {
        return Double.compare(this.getDistance(), p2.getDistance());
    }

    @Override
    public String toString() {
        return "Point{" +
                "point=" + Arrays.toString(point) +
                ", distance=" + distance +
                '}';
    }
}
class Solution {
    Random rand= new Random();

    // use Hoare Partition for points.length() is not very large
    boolean pointCloser(int[] p1, int[] p2) {
        // euclidean distance: p1 < p2
        return Double.compare(
                p1[0]*p1[0]+p1[1]*p1[1], p2[0]*p2[0]+p2[1]*p2[1]) < 0;
    }
    public void swap(int[][] points, int index1,int index2){
        int[] temp=points[index1];
        points[index1]=points[index2];
        points[index2]=temp;
    }
    public int partition(int[][] points, int low, int high) {
        int pivotId= low+rand.nextInt(high-low+1);
        swap(points, low, pivotId);
        int[] temp = points[low];
        while (low < high) {
            // pTemp < pHigh
            while (low < high && pointCloser(temp, points[high]))
                high--;
            points[low] = points[high];
            // pLow <= pTemp
            while (low < high && !pointCloser(temp, points[low]))
                low++;
            points[high] = points[low];
        }
        points[low] = temp;
        return low;
    }
    public int[][] kClosest(int[][] points, int k) {
        if (points.length == 1) return points;

        int start = 0, end = points.length - 1;
        while (start < end) {
            int pivot = partition(points, start, end);
            if (pivot == k) break;
            else if (pivot < k) {
                start = pivot + 1;
            } else {
                end = pivot - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, k);
    }

    // use priority queue to maintain top K int[2](instead of Point class)
    public int[][] kClosest1(int[][] points, int k) {
        if (points.length == 1) return points;

        int[][] ans = new int[k][2];
        PriorityQueue<int[]> pq = new PriorityQueue<>(k,
                Comparator.comparingInt(o -> (o[0] * o[0] + o[1] * o[1])));
        // 构建 K最小堆，堆顶是当前距离最近点
        // for (int[] point : points) pq.offer(point);
        pq.addAll(Arrays.asList(points));

        while (k > 0) ans[--k] = pq.poll();
        return ans;
    }

    // use priority queue to maintain top K Point(custom class)
    public int[][] kClosest2(int[][] points, int k) {
        if (points.length == 1) return points;

        PriorityQueue<Point> pq = new PriorityQueue<>(k, (o1, o2) -> -o1.compareTo(o2));
        // 构建 K最大堆，堆顶是当前距离最远点
        for (int i = 0; i < k; i++) {
            pq.add(new Point(points[i]));
        }
        for (int i = k; i < points.length; i++) {
            Point now = new Point(points[i]);
            Point head = pq.element();
            int compare = now.compareTo(head);
            if (compare < 0) {
                pq.remove();
                pq.add(now);
            }
        }
        int[][] ans = new int[pq.size()][];
        while (!pq.isEmpty()) {
            ans[--k] = pq.remove().getPoint();
        }
        return ans;
    }

    public static int[][] createTwoDimArray(int[] nums, int m, int n) {
        if (m <= 0 || n <= 0) {
            System.out.println("m or n is <= 0");
            return null;
        }
        int k = 0;
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            matrix[i] = new int[n];
            for (int j = 0; j < n; j++) {
                matrix[i][j] = nums[k++];
            }
        }
        return matrix;
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();
        // test1: points = [[1,3],[-2,2]], k = 1, Output: [[-2,2]]
        int[] s1 = new int[] {1,3,-2,2};
        System.out.println(Arrays.deepToString(s.kClosest(Solution.createTwoDimArray(s1, 2, 2), 1)));
        // test2: points = [[3,3],[5,-1],[-2,4]], k = 2, Output: [[3,3],[-2,4]]
        int[] s2 = new int[] {3,3,5,-1,-2,4};
        System.out.println(Arrays.deepToString(s.kClosest(Solution.createTwoDimArray(s2, 3, 2), 2)));
        // test3: points = [[3,3],[3,3],[-3,3],[-2,4],[7,9]], k = 3, Output: [[3,3],[3,3],[-3,3]]
        int[] s3 = new int[] {3,3,3,3,-3,3,-2,4,7,9};
        System.out.println(Arrays.deepToString(s.kClosest(Solution.createTwoDimArray(s3, 5, 2), 3)));
    }
}