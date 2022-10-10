/* 299. Bulls and Cows
 *
 * */

import java.util.*;

class Solution {
    private static final char A = 'A', B = 'B';
    // get rid of HashMap
    public String getHint(String secret, String guess) {
        int x = 0, y = 0;
        int[] map = new int[10], map2 = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i), g = guess.charAt(i);
            if (s == g)
                x++;
            else {
                map[s - '0']++;
                map2[g - '0']++;
            }
        }
        for (int i = 0; i < 10; i++) {
            y += Math.min(map[i], map2[i]);
        }
        return String.valueOf(x) + A + y + B;
    }
    // HashMap, optimized
    public String getHint2(String secret, String guess) {
        int x = 0, y = 0;
        HashMap<Character, Integer> map = new HashMap<>(secret.length());
        HashMap<Character, Integer> map2 = new HashMap<>(secret.length());
        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i), g = guess.charAt(i);
            if (s == g) x++;    // optimize here
            else {
                map.put(s, map.getOrDefault(s, 0) + 1);
                map2.put(g, map2.getOrDefault(g, 0) + 1);
            }
        }
        for (char c : map.keySet()) {
            int mapCount = map.getOrDefault(c, 0);
            int map2Count = map2.getOrDefault(c, 0);
            y += Math.min(mapCount, map2Count);
        }
        return String.valueOf(x) + A + y + B;
    }
    // HashMap, but slow
    public String getHint1(String secret, String guess) {
        int x = 0, y = 0;
        HashMap<Character, HashSet<Integer>> map = new HashMap<>(secret.length());
        HashMap<Character, HashSet<Integer>> map2 = new HashMap<>(secret.length());
        for (int i = 0; i < secret.length(); i++) {
            char c = secret.charAt(i);
            HashSet<Integer> posList = map.getOrDefault(c, new HashSet<>(secret.length()));
            posList.add(i + 1);  // make index 0 to 1
            map.put(c, posList);
        }
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            HashSet<Integer> posList = map2.getOrDefault(c, new HashSet<>(guess.length()));
            posList.add(i + 1);  // make index 0 to 1
            map2.put(c, posList);
        }
        for (char c : map.keySet()) {
            HashSet<Integer> map1set = map.get(c);
            HashSet<Integer> map2set = map2.get(c);
            int count = 0;
            if (map2set == null) continue;
            for (int pos : map2set) {
                if (map1set.contains(pos))
                    count++;
            }
            x += count;
            if (count < map1set.size()) {
                y += Math.min(map1set.size(), map2set.size()) - count;
            }
        }
        return String.valueOf(x) + A + y + B;
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();
        // test1: secret="1", guess="1", output: "1A0B"
        System.out.println(s.getHint("1", "1"));
        // test2: secret="1", guess="0", output: "0A0B"
        System.out.println(s.getHint("1", "0"));
        // test3: secret="12", guess="11", output: "1A0B"
        System.out.println(s.getHint("12", "11"));
        // test4: secret="12", guess="21", output: "0A2B"
        System.out.println(s.getHint("12", "21"));
        // test5: secret="123", guess="321", output: "1A2B"
        System.out.println(s.getHint("123", "321"));
        // test6: secret="121", guess="112", output: "1A2B"
        System.out.println(s.getHint("121", "112"));
        // test7: secret="1807", guess="7810", output: "1A3B"
        System.out.println(s.getHint("1807", "7810"));
        // test8: secret="1123", guess="0111", output: "1A1B"
        System.out.println(s.getHint("1123", "0111"));
        // test8: secret="1122", guess="1222", output: "3A0B"
        System.out.println(s.getHint("1122", "1222"));
    }
}