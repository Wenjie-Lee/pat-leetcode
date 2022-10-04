
/*
 * 278. First Bad Version
 * You are a product manager and currently leading a team to develop a new product. Unfortunately,
 * the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
 * which causes all the following ones to be bad.
 * You are given an API bool isBadVersion(version) which returns whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to the API.
 *
 * 给定一个递增整数序列n，给定一个目标数，找到序列中第一个出现目标数的位置，否则返回-1
 * 给定了一个isBadVersion(version) API，尽量减少调用次数
 *
 * Example 1:
 * Input: n = 5, bad = 4
 * Output: 4
 * Explanation:
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 *
 * Example 2:
 * Input: n = 1, bad = 1
 * Output: 1
 *
 * Constraints:
 * 1 <= bad <= n <= 2^31 - 1
 * */

/*
 * 寻找第一个大于等于bad的数
 * 二分查找拓展
 * */

class Solution {
    boolean isBadVersion(int version, int bad) {
        return version >= bad;
    }

    /*
     * 寻找第一个大于等于bad的数
     * 二分查找拓展
     * */
    public int firstBadVersion(int n, int bad) {
        int count = 1;

        if (isBadVersion(1, bad)) {
            System.out.printf("count: %d, first bad: ", count);
            return 1;
        }

        int low = 1, high = n;
        while (low < high) {
            int mid = ((high - low) / 2) + low;
            count ++;
            // 如果mid >= bad，则一定在区间[low, mid]
            if (isBadVersion(mid, bad)) high = mid;
                // 可能在(mid, high]
            else low = mid + 1;
        }
        System.out.printf("count: %d, first bad: ", count);
        return low;
    }

    public int firstBadVersion1(int n, int bad) {
        int count = 1;

        if (isBadVersion(1, bad)) {
            System.out.printf("count: %d, first bad: ", count);
            return 1;
        }

        // CRITICAL
        // 1 <= bad <= n <= 2^31 - 1
        // int mid = (low + high) >> 1; 会超出范围
        int low = 1, high = n;
        while (low <= high) {
            int mid = ((high - low) / 2) + low;
            count ++;
            if (isBadVersion(mid, bad)) {
                if (mid == 1) {
                    System.out.printf("count: %d, first bad: ", count);
                    return 1;
                }
                else if (!isBadVersion(mid - 1, bad)) {
                    System.out.printf("count: %d, first bad: ", count);
                    count ++;
                    return mid;
                }
                else {
                    high = mid - 1;
                }
                count ++;
            }
            else
                low = mid + 1;
        }
        System.out.printf("count: %d, first bad: ", count);
        return n;
    }
}

class Test {

    public static void main(String[] args) {
        Solution s = new Solution();

//         test 1 n = 1, bad = 1
        System.out.println(s.firstBadVersion(1, 1));
        // test 2 n = 5, bad = 5
        System.out.println(s.firstBadVersion(5, 5));
        // test 3 n = 5, bad = 4
        System.out.println(s.firstBadVersion(5, 4));
//         test 4 n = 2126753390, bad = 1702766719
        System.out.println(s.firstBadVersion(2126753390, 1702766719));
    }
}