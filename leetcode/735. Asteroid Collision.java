/*735. Asteroid Collision
 * 给定一个int[]，数组中值代表陨石大小，符号不同会相撞，大小不同只保留大的，相同则都不保留
 * +表示向右走，-表示向左走
 * 求碰撞后的int[]
 *
 * Constraints:
 * 2 <= asteroids.length <= 10^4
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 * */

import java.util.Arrays;
import java.util.Stack;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();

        for (int asteroid : asteroids) {
            // asteroid > 0, --->
            if (asteroid > 0) st.push(asteroid);
                // asteroid < 0, <---
            else {
                // -> <---
                while (!st.isEmpty() && st.peek() > 0 && st.peek() < -asteroid)
                    st.pop();
                // <--- or <- <---
                if (st.isEmpty() || st.peek() < 0)
                    st.push(asteroid);
                // --> <--
                if (st.peek() == -asteroid)
                    st.pop();
                // ---> <- , do nothing
            }
        }
        int[] rest = new int[st.size()];
        for (int i = rest.length-1; i >= 0; i--) {
            rest[i] = st.pop();
        }
        return rest;
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();

        // test 1 [10] | [-5] | []
        System.out.println(Arrays.toString(s.asteroidCollision(new int[]{10})));
        System.out.println(Arrays.toString(s.asteroidCollision(new int[]{-5})));
        // test 2 [10, 10] | [10, 5] | [-5, 8] | [-10, 8] -> [10,10] | [10,5] | [8]
        System.out.println(Arrays.toString(s.asteroidCollision(new int[]{10, 10})));
        System.out.println(Arrays.toString(s.asteroidCollision(new int[]{10, 5})));
        System.out.println(Arrays.toString(s.asteroidCollision(new int[]{-5, 8})));
        System.out.println(Arrays.toString(s.asteroidCollision(new int[]{-10, 8})));
        // test 4 [5,10,-5] | [10,2,-5] -> [5,10] | [10]
        System.out.println();
        System.out.println(Arrays.toString(s.asteroidCollision(new int[]{5,10,-5})));
        System.out.println(Arrays.toString(s.asteroidCollision(new int[]{10,2,-5})));
        // test 5 [10,-5,-5,-5,-5,-5,-5,-5] -> [10]
        System.out.println();
        System.out.println(Arrays.toString(s.asteroidCollision(new int[]{10,-5,-5,-5,-5,-5,-5,-5})));
        // test 6 [-5,-5,-5,-5,-5,-5,-5,10] -> [10]
        System.out.println(Arrays.toString(s.asteroidCollision(new int[]{-5,-5,-5,-5,-5,-5,-5,10})));
        // test 7 [-5,-5,-5,-5,-5,-5,-5,2] -> [-5,-5,-5,-5,-5,-5,-5]
        System.out.println(Arrays.toString(s.asteroidCollision(new int[]{-5,-5,-5,-5,-5,-5,-5,2})));
//        // test 8 [-2,-1,1,2] -> [-2,-1,1,2]
        System.out.println(Arrays.toString(s.asteroidCollision(new int[]{-2,-1,1,2})));

    }
}