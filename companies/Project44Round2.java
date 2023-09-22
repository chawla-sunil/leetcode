
import java.util.HashMap;
import java.util.Map;

public class Project44Round2 {
//    Implement LRU cache
//    public static void main(String[] args) {
//    }
    private Map<Integer, Node> map = new HashMap<>();
    private int capacity, count;
    private Node head, tail;

    public Project44Round2(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0,0);
        tail = new Node(0,0);

        head.next = tail;
        tail.pre = head;
        head.pre = null;
        tail.next = null;
        count = 0;
    }

    public void set(int key, int value) {
        if (map.get(key) == null) {
            Node node = new Node(key, value);
            map.put(key, node);

            if (count < capacity) {
                count++;
                setToHead(node);
            } else {
                map.remove(tail.pre.key);

                removeNode(node);

                setToHead(node);
            }
        } else {
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            setToHead(node);
        }
    }

    public void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public int get(int key) {
        if (map.get(key) != null) {
            Node node = map.get(key);
            int value = node.value;
            removeNode(node);
            setToHead(node);
            return value;
        }
        return -1;
    }

    public void setToHead(Node node) {
        node.next = head.next;
        node.next.pre = node;
        node.pre = head;
        head.next = node;
    }


    class Node {
        int key;
        int value;
        Node pre;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }


}
