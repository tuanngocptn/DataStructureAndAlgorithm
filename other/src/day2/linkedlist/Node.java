package day2.linkedlist;

public class Node {
    protected int info; //data node
    protected Node next; //address next node

    public Node() {

    }

    public Node(int info, Node next) {
        this.info = info;
        this.next = next;
    }
}
