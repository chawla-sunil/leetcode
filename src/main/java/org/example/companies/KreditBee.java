package org.example.companies;

public class KreditBee {
    //  31 July 2026
    public static class Node {
        public int key;
        public int value;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public static class LinkedList {
        private Node head;

        public void add(int key, int value) {
            Node newNode = new Node(key, value);

            if (head == null) {
                head = newNode;
                return;
            }

            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        public void traverse() {
            Node current = head;
            while (current != null) {
                System.out.println("key: " + current.key + " , value: " + current.value);
                current = current.next;
            }
        }

        public void delete(int key) {
            if (head == null) {
                return;
            }

            if (head.key == key) {
                head = head.next;
                return;
            }

            Node current = head;
            while (current.next != null) {
                if (current.next.key == key) {
                    current.next = current.next.next;
                    return;
                }
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {
        // linkedList => add (1,1), (2,2), (3,3)
        // add , traverse, delete,

        LinkedList linkedList = new LinkedList();
        linkedList.add(1, 1);
        linkedList.add(2, 2);
        linkedList.add(3, 3);
//        linkedList.traverse();
        linkedList.delete(2);
        linkedList.delete(3);
//        linkedList.traverse();
        linkedList.add(4, 4);
//        linkedList.traverse();
        linkedList.delete(4);
        linkedList.delete(1);
//        linkedList.traverse();
        linkedList.add(1, 1);
        linkedList.traverse();
    }
}
