/* 146. LRU Cache
 * 设计一个数据结构，实现最近最少使用cache
 *
 * */

import java.util.*;

// use LinkedHashMap `s removeEldestEntry
// LinkedHashMap maintains a doubly-linked list to record the insertion order
// In the Android source code they use a LinkedHashMap as well
// https://android.googlesource.com/platform/frameworks/support.git/+/795b97d901e1793dac5c3e67d43c96a758fec388/v4/java/android/support/v4/util/LruCache.java
class LRUCache1 {
    private final LinkedHashMap<Integer, Integer> cache;
    private final int CAPACITY;

    public LRUCache1(int capacity) {
        CAPACITY = capacity;
        cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > CAPACITY;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}

// a manually-implemented version
class LRUCache {
    // LinkedHashMap.Node<K, V> extends HashMap.Node<K, V>
    // a Node subclass for order maintenance
    static class Node {
        int key, value;
        Node before, after;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
        public Node() { this(0, 0);}
    }
    private final HashMap<Integer, Node> cache;
    private final int CAPACITY;
    private final Node head, tail;

    public LRUCache(int capacity) {
        CAPACITY = capacity;
        cache = new HashMap<>(capacity, 0.75f);
        head = new Node();
        tail = new Node();
        head.after = this.tail;
        tail.before = this.head;
    }

    public int get(int key) {
        Node n = cache.get(key);
        if (n == null) return -1;
        update(n);      // reorder the node to top
        return n.value;
    }

    public void put(int key, int value) {
        Node n = cache.get(key);
        if (n == null) {    // newly insertion
            n = new Node(key, value);
            cache.put(key, n);
            add(n);
        } else {            // change the value, and reorder to top
            n.value = value;
            update(n);
        }
        if (cache.size() > CAPACITY) {  // delete the tail node if exceed the CAPACITY
            Node deleteTail = tail.before;
            remove(deleteTail);
            cache.remove(deleteTail.key);
        }
    }
    // add Node to top
    private void add(Node node) {
        Node headNext = head.after;
        head.after = node;
        node.before = head;
        node.after = headNext;
        headNext.before = node;
    }
    // remove Node, remove the tail one in the vast majority of cases
    private void remove(Node node) {
        Node deletePrev = node.before, deleteNext = node.after;
        deletePrev.after = deleteNext;
        deleteNext.before = deletePrev;
    }
    // update the existing node, first remove, then add to top
    private void update(Node node) {
        remove(node);
        add(node);
    }
}


class Test {
    public static void main(String[] args) {

    }
}