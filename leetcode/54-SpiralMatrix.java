/*54. Spiral Matrix
 * 给定一个二维矩阵，按照螺旋向内遍历输出所有元素
 *
 *
 * */
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null) return null;

        int m = matrix.length, n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            visited[i] = new boolean[n];
        }

        List<Integer> output = new ArrayList<>(m * n);
        int i = 0, j = 0, k = 0;
        while (k < m * n) {
            while (k < m * n && j < n && !visited[i][j]) {
                visited[i][j] = true;
                output.add(matrix[i][j++]);
                k++;
            }
            i += 1; j -= 1;
            while (k < m * n && i < m && !visited[i][j]) {
                visited[i][j] = true;
                output.add(matrix[i++][j]);
                k++;
            }
            i -= 1; j -= 1;
            while (k < m * n && j >= 0 && !visited[i][j]) {
                visited[i][j] = true;
                output.add(matrix[i][j--]);
                k++;
            }
            i -= 1; j += 1;
            while (k < m * n && i >= 0 && !visited[i][j]) {
                visited[i][j] = true;
                output.add(matrix[i--][j]);
                k++;
            }
            i += 1; j += 1;
        }
        return output;
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

        //test 1
        int[][] i1 = new int[][] {new int[] {1}};
        System.out.println(s.spiralOrder(i1));

        // test 2
        int[][] i2 = Solution.createTwoDimArray(new int[] {1,2,3,4,5,6,7,8,9}, 3, 3);
        System.out.println(s.spiralOrder(i2));

        // test 3
        int[][] i3 = Solution.createTwoDimArray(new int[] {1,2,3,4,5,6,7,8,9,10,11,12}, 3, 4);
        System.out.println(s.spiralOrder(i3));

        //test 4
        int[][] i4 = Solution.createTwoDimArray(new int[] {1,2,3}, 3, 1);
        System.out.println(s.spiralOrder(i4));

        //test 5
        int[][] i5 = Solution.createTwoDimArray(new int[] {1,2,3}, 1, 3);
        System.out.println(s.spiralOrder(i5));
    }
}