package util;

public class LinkedList<E> {
    private Node head = null;
    private Node tail = null;
    private Node current = null;

    class Node {
        E e;
        Node next;
        Node previous;

        private Node() {
        }

        private Node(E e, Node next, Node previous) {
            this.e = e;
            this.next = next;
            this.next = next;
            this.previous = previous;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int length() {
        Node node = head;
        int sum = 0;
        while (node != null) {
            sum++;
            node = node.next;
        }
        return sum;
    }

    public void insertFirst(E value) {
        if (isEmpty()) {
            Node node = new Node(value, null, null);
            head = node;
            tail = node;
        } else {
            Node node = new Node(value, head, null);
            head.previous = node;
            head = node;
        }
    }

    public void insertLast(E value) {
        if (isEmpty()) {
            Node node = new Node(value, null, null);
            head = node;
            tail = node;
        } else {
            Node node = new Node(value, null, tail);
            tail.next = node;
            tail = node;
        }
    }

    public E deleteFirst() {
        Node node = head;
        if (length() > 1) {
            head = head.next;
            head.previous = null;
        } else {
            head = null;
        }
        return node.e;
    }

    public E deleteLast() {
        Node node = tail;
        tail = tail.previous;
        tail.next = null;
        return node.e;
    }

    public E delete(int location) {
        if (location < 1) deleteFirst();
        else if (location > length() - 2) deleteLast();
        else {
            Node node = head;
            for (int i = 0; i < location - 1; i++) {
                node = node.next;
            }
            Node nodeDel = node.next;
            Node nodeAft = nodeDel.next;
            node.next = nodeAft;
            return nodeDel.e;
        }
        return null;
    }

    public void insertAt(int location, E value) {
        if (location < 1) insertFirst(value);
        else if (location > length() - 1) insertLast(value);
        else {
            Node node = head;
            for (int i = 0; i < location - 1; i++) {
                node = node.next;
            }
            Node nodeNew = new Node(value, node.next, node);
            node.next.previous = nodeNew;
            node.next = nodeNew;
        }
    }

    public E getAt(int location) {
        if (location < 1) return head.e;
        if (location > length() - 2) return tail.e;
        Node node = head;
        for (int i = 0; i < location; i++) {
            node = node.next;
        }
        return node.e;
    }
}
