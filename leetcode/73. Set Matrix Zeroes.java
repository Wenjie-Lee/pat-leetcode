/* 73. Set Matrix Zeroes
 *
 *
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

class Solution {
    // Memory cost O(1), Time cost O(mn)
    // use matrix to record marks
    public void setZeroes(int[][] matrix) {
        if (matrix == null) return;
        boolean firstColumn = false, firstRow = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // make its column&row `s first element to 0
                if (matrix[i][j] == 0) {
                    if (i == 0) firstRow = true;
                    if (j == 0) firstColumn = true;
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                // to 0 if the first element in this row&column is 0
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstColumn) {  // change the first column to 0
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
        if (firstRow) {     // change the first row to 0
//            Arrays.fill(matrix[0], 0);
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
    }
    // Memory cost O(m+n) in worst case, Time cost O(mn)
    public void setZeroes3(int[][] matrix) {
        if (matrix == null) return;
        int m = matrix.length, n = matrix[0].length;
        // positions stored in Map.Entry<Integer, Integer> have duplicated cases
        // stored in two set will remove the duplicated i or j
        HashSet<Integer> seti = new HashSet<>();
        HashSet<Integer> setj = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    seti.add(i);
                    setj.add(j);
                }
            }
        }
        for (int i : seti) {
            for (int j = 0; j < n; j++) matrix[i][j] = 0;
        }
        for (int j : setj) {
            for (int i = 0; i < m; i++) matrix[i][j] = 0;
        }
    }

    // Memory cost O(mn) in worst case, Time cost O(mn)
    public void setZeroes2(int[][] matrix) {
        if (matrix == null) return;
        int m = matrix.length, n = matrix[0].length;
        HashSet<Map.Entry<Integer, Integer>> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    set.add(Map.entry(i, j));
//                    for (int k = 0; k < m; k++) set.add(Map.entry(k, j));
//                    for (int k = 0; k < n; k++) set.add(Map.entry(i, k));
                }
            }
        }
        for (Map.Entry<Integer, Integer> it : set) {
//            matrix[it.getKey()][it.getValue()] = 0;
            int i = it.getKey(), j = it.getValue();
            for (int k = 0; k < m; k++) matrix[k][it.getValue()] = 0;
            for (int k = 0; k < n; k++) matrix[it.getKey()][k] = 0;
        }
    }

    // Memory cost O(mn), Time cost O(mn)
    public void setZeroes1(int[][] matrix) {
        if (matrix == null) return;
        int m = matrix.length, n = matrix[0].length;
        int[][] visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = 0;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 0 && matrix[i][j] == 0) {
                    for (int k = 0; k < m; k++) {
                        if (visited[k][j] == 0 && matrix[k][j] != 0) {
                            matrix[k][j] = 0;
                            visited[k][j] = 1;
                        }
                    }
                    for (int k = 0; k < n; k++) {
                        if (visited[i][k] == 0 && matrix[i][k] != 0) {
                            matrix[i][k] = 0;
                            visited[i][k] = 1;
                        }
                    }
                }
            }
        }
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

        // test 1: [0]
        int[][] m1 = Solution.createTwoDimArray(new int[] {0}, 1,1);
        System.out.println("test1:  Matrix");
        System.out.println(Arrays.deepToString(m1));
        System.out.println("Should: \n[[0]]\nSolution:");
        s.setZeroes(m1);
        System.out.println(Arrays.deepToString(m1));
        // test 2: [[1,1,1],[1,0,1],[1,1,1]], Output: [[1,0,1],[0,0,0],[1,0,1]]
        int[][] m2 = Solution.createTwoDimArray(new int[] {1,1,1,1,0,1,1,1,1}, 3,3);
        System.out.println("test2: Matrix");
        System.out.println(Arrays.deepToString(m2));
        System.out.println("Should: \n[[1, 0, 1], [0, 0, 0], [1, 0, 1]]\nSolution:");
        s.setZeroes(m2);
        System.out.println(Arrays.deepToString(m2));
        // test 3: [[0,1,2,0],[3,4,5,2],[1,3,1,5]], Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
        int[][] m3 = Solution.createTwoDimArray(new int[] {0,1,2,0,3,4,5,2,1,3,1,5}, 3,4);
        System.out.println("test3: Matrix");
        System.out.println(Arrays.deepToString(m3));
        System.out.println("Should: \n[[0, 0, 0, 0], [0, 4, 5, 0], [0, 3, 1, 0]]\nSolution:");
        s.setZeroes(m3);
        System.out.println(Arrays.deepToString(m3));

    }
}