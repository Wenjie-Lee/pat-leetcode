/*264. Ugly Number II
 *
 * */

/*
 * An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
 * */

class Solution {
    // dp[i] = min(dp[idx2], dp[idx3], dp[idx5]), min(idx2, idx3, idx5)++
    // 2, 2x2, 3x2, 4x2, 5x2, ... add to dp[idx2]
    // 3, 2x3, 3x3, 4x3, 5x3, ... add to dp[idx3]
    // 5, 2x5, 3x5, 4x5, 5x5, ... add to dp[idx5]
    public int nthUglyNumber1(int n) {
        if (n <= 6) return n;

        int f2 = 2, f3 = 3, f5 = 5;
        int idx2 = 0, idx3 = 0, idx5 = 0;
        int[] ugly = new int[n];
        ugly[0] = 1;
        for (int i = 1; i < n; i++) {
            int minUgly = Integer.min(Integer.min(f2, f3), f5);
            ugly[i] = minUgly;
            if (minUgly == f2)
                f2 = 2 * ugly[++idx2];
            if (minUgly == f3)
                f3 = 3 * ugly[++idx3];
            if (minUgly == f5)
                f5 = 5 * ugly[++idx5];
        }
        return ugly[n-1];
    }
    public int nthUglyNumber(int n) {
        if (n <= 6) return n;
        int[] dp = new int[n];
        int dp2 = 0, dp3 = 0, dp5 = 0;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int new2 = dp[dp2] * 2, new3 = dp[dp3] * 3, new5 = dp[dp5] * 5;
            int minUgly = Integer.min(
                    Integer.min(new2, new3), new5
            );
            dp[i] = minUgly;
            if (minUgly == new2) dp2++;
            if (minUgly == new3) dp3++;
            if (minUgly == new5) dp5++;
        }
        return dp[n-1];
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();
        // test1: n = 1 -> 1
        System.out.println(s.nthUglyNumber(1));
        // test1: n = 10 -> 12
        System.out.println(s.nthUglyNumber(10));
    }
}