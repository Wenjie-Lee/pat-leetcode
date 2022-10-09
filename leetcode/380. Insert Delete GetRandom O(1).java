/*380. Insert Delete GetRandom O(1)
 * 要求所有方法都满足O(1)时间复杂度
 * */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

// 利用一个ArrayList存储val，得到一个索引index，利用HashMap存储<val, index>
// 当插入时，若HashMap中无，则插入HashMap<val, ArrayList.size()>，并在ArrayList末尾插入val
// 当删除时，若HashMap中有，则删除并返回一个index，ArrayList根据index获取val，
//  且将此index与末尾互换，然后删除末尾，保持与HashMap长度一致，
//  且在HashMap中找到末尾val的节点，更新其index
// 当随机输出值时，随机给定[0, ArrayList.size())中一值rand，返回ArrayList[rand]
class RandomizedSet {
    private HashMap<Integer, Integer> val2idx;
    private ArrayList<Integer> values;
    Random random;

    public RandomizedSet() {
        val2idx = new HashMap<>();
        values = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (val2idx.containsKey(val)) return false;

        val2idx.put(val, values.size());
        values.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!val2idx.containsKey(val)) return false;

        int idx = val2idx.get(val);
        // swap the idx to tail in ArrayList
        if (idx < values.size() - 1) {
            int tailValue = values.get(values.size() - 1);
            // values.set(values.size() - 1, idx); redundant cus this will be removed later
            values.set(idx, tailValue);
            // the tail element swap to idx, update HashMap<tailVal, oldIdx> to <tailVal, idx>
            val2idx.replace(tailValue, idx);    // or put(), replace() did`t check if it exists
        }
        val2idx.remove(val);
        values.remove(values.size() - 1);
        return true;
    }

    public int getRandom() {
        return values.get(random.nextInt(values.size()));
    }
}
// 在大量添加大量删除后，[0, currentIndex]存在很多空位，会导致while循环耗时越来越长
class RandomizedSet2 {
    private static final int MAX_SIZE = 2 * 100000;
    private HashMap<Integer, Integer> idx2val;
    private HashMap<Integer, Integer> val2idx;
    Random random;
    private int currentIndex;

    public RandomizedSet2() {
        idx2val = new HashMap<>();
        val2idx = new HashMap<>();
        random = new Random();
        currentIndex = 0;
    }

    public boolean insert(int val) {
        if (!val2idx.containsKey(val)) {
            idx2val.put(currentIndex, val);
            val2idx.put(val, currentIndex++);
            return true;
        }
        return false;
    }

    public boolean remove(int val) {
        if (!val2idx.containsKey(val)) {
            return false;
        }
        int idx = val2idx.get(val);
        val2idx.remove(val);
        idx2val.remove(idx);
        return true;
    }

    public int getRandom() {
        while (true) {
            int rand = random.nextInt(0, currentIndex);
            if (idx2val.containsKey(rand))
                return idx2val.get(rand);
        }
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

class Test {
    public static void main(String[] args) {

    }
}