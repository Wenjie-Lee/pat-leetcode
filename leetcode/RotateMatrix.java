import java.util.Arrays;

/*
 *
 * */
class Solution {
    public void rotate1(int[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length == 1) return;

        int len = matrix.length;
        for (int k = 0; k < len; k++) {
            for (int l = k; l < len - 1 - k; l++) {
                if (k == l) {     // 角情况
                    int t1 = matrix[k][l];
                    matrix[k][l] = matrix[len - 1 - k][l];
                    matrix[len - 1 - k][l] = matrix[len - 1 - k][len - 1 - l];
                    matrix[len - 1 - k][len - 1 - l] = matrix[k][len - 1 - l];
                    matrix[k][len - 1 - l] = t1;
                }
                else {
                    int k1 = k, l1 = l;
                    int k3 = len - 1 - k, l3 = len - 1 - l;
                    int k2 = l, l2 = len - 1 - k;
                    int k4 = len - 1 - l, l4 = k;
                    int t1 = matrix[k1][l1];
                    matrix[k1][l1] = matrix[k4][l4];
                    matrix[k4][l4] = matrix[k3][l3];
                    matrix[k3][l3] = matrix[k2][l2];
                    matrix[k2][l2] = t1;
                }
            }
        }
    }

    void swap(int[][] matrix, int ia, int ib, int ja, int jb) {
        int temp = 0;
        temp = matrix[ia][ib];
        matrix[ia][ib] = matrix[ja][jb];
        matrix[ja][jb] = temp;
    }
    public void rotate(int[][] matrix) {
        // transpose the matrix
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < i; j++){
                swap(matrix, i, j, j ,i);
            }
        }
        // flip the matrix horizontally
        for(int i = 0 ; i < matrix.length; i++){
            for(int j = 0; j < matrix.length / 2; j++){
                swap(matrix, i, j, i, matrix.length-1-j);
            }
        }
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();
        //test 1 matrix = [[1]]
        int[][] m1 = new int[][] {new int[] {1}};
        s.rotate(m1);
        System.out.println(Arrays.deepToString(m1));
        //test 2 matrix = [[1,2,3],[4,5,6],[7,8,9]], output: [[7,4,1],[8,5,2],[9,6,3]]
        int[][] m2 = new int[][] {new int[] {1,2,3}, new int[] {4,5,6}, new int[] {7,8,9}};
        s.rotate(m2);
        System.out.println(Arrays.deepToString(m2));
        // test 3 matrix = [[1,2],[3,4]], output: [[3,1],[4,2]]
        int[][] m3 = new int[][] {new int[] {1,2}, new int[] {3,4}};
        s.rotate(m3);
        System.out.println(Arrays.deepToString(m3));
        // test 4 matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]],
        // output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
        int[][] m4 = new int[][] {new int[] {5,1,9,11}, new int[] {2,4,8,10}, new int[] {13,3,6,7}, new int[] {15,14,12,16}};
        s.rotate(m4);
        System.out.println(Arrays.deepToString(m4));
    }
}