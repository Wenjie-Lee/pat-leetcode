import java.util.*;

/*49. Group Anagrams
 *
 *
 * */
class Solution {
    // O(n * 26) ~ O(78n)
    public List<List<String>> groupAnagrams1(String[] strs) {
        if (strs == null || strs.length == 0) return null;

        HashMap<String, LinkedList<String>> map = new HashMap<>();
        // O(n * (26 * 3))
        for (String str : strs) {
            // use bit map instead of sorting
            // O(26), k∈[0, 26]
            char[] chars = new char[26];
            // O(26)
            for (char c : str.toCharArray()) chars[c - 'a']++;
            // O(26)
            String bitMapKey = String.valueOf(chars);
            // O(1)
            LinkedList<String> tempList = map.getOrDefault(bitMapKey, new LinkedList<>());
            // O(1)
            tempList.add(str);
            map.put(bitMapKey, tempList);
        }
        // O(n)
        return new LinkedList<>(map.values());
    }

    // O(nlogk)
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return null;

        HashMap<String, List<String>> map = new HashMap<>();
        // O(nlogk)
        for (String str : strs) {
            // O(k), k∈[0, 100]
            char[] chars = str.toCharArray();
            // O(klogk)
            Arrays.sort(chars);
            // O(k)
            String sorted = String.valueOf(chars);
            // O(1)
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<>());
            }
            // O(1)
            map.get(sorted).add(str);
        }
        // O(n)
        return new ArrayList<>(map.values());
    }
}

class Test {
    public static void main(String[] args) {
        Solution s = new Solution();

        // test 0: strs = [""]
        // Output: [[""]]
        String[] s0 = new String[]{""};
        System.out.println(s.groupAnagrams(s0));
        // test 1: strs = ["eat","tea","tan","ate","nat","bat"],
        // Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
        String[] s1 = new String[]{"eat","tea","tan","ate","nat","bat"};
        System.out.println(s.groupAnagrams(s1));
        // test 2: strs = ["","a","ab","abc","ba","c"],
        // Output: [[""],["a"],["c"],[["ab","ba"]],[["abc"]]
        String[] s2 = new String[]{"","a","ab","abc","ba","c"};
        System.out.println(s.groupAnagrams(s2));
    }
}