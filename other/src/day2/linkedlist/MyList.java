package day2.linkedlist;

public class MyList {
    Node head, tail; //first and last node

    public MyList() {

    }

    //check list empty
    public boolean isEmpty() {
        return head == null;
    }

    //inser node into list
    public void insert(int x) {
        if (isEmpty()) {
            Node p;
            head = tail = new Node(x, null);
        } else {
            Node p = new Node(x, null);
            tail.next = p;
            tail = p;
        }
    }

    public void print() {
        Node p = head;
        while (p != null) {
            System.out.print("->" + p.info);
            p = p.next;
        }
    }


    public int coundNode() {
        int count = 0;
        Node p = head;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }

    public int sumNode() {
        int count = 0;
        Node p = head;
        while (p != null) {
            count += p.info;
            p = p.next;
        }
        return count;
    }

    // add node in first element of list
    public void insertFirst(int x) {
        if (isEmpty()) {
            head = tail = new Node(x, null);
        } else {
            Node p = new Node(x, head);
            head = p;

        }
    }

    // add node into k position
    public void insertAfter(int x, int k) {
        Node q = new Node(x, null);
        Node p = search(k);
        q.next = p.next;
        p.next = q;
        if (p.info == tail.info) {
            tail = q;
        }
    }

    // search by info
    public Node search(int info) {
        if (isEmpty()) {
            System.out.println("Not found !!!");
        } else {
            Node p = head;
            while (p != null) {
                if (p.info == info) {
                    return p;
                }
                p = p.next;
            }
        }
        return null;
    }

    // search by position
    public Node searchByPosition(int position) {
        if (isEmpty()) {
            System.out.println("Not found !!!");
        } else {
            int count = 0;
            Node p = head;
            while (p != null) {
                if (count == position) {
                    return p;
                }
                count++;
                p = p.next;
            }
        }
        return null;
    }

    //Delete
    public void deleteTailNode() {
        Node q = head;
        Node p = head.next;
        if (!isEmpty()) {
            while (p != null) {
                if (p.next.next == null) {
                    tail = p;
                    q.next = tail;
                    tail.next = null;
                    break;
                }
                q = q.next;
                p = p.next;
            }
        }
    }

    public void deleteFirst() {
        Node p = head.next;
        head = p;
    }

    public void delete(int x) {
        if (!isEmpty()) {
            Node q = head;
            while (q != null) {
                if (q.next.info == x) {
                    q.next = q.next.next;
                    return;
                }
                q = q.next;
            }
        } else {
            System.out.println("do nothing");
        }
    }

    public void deletePosition(int x) {

        if (!isEmpty()) {
            Node q = head;
            int i = 0;
            while (q != null) {
                if (i==x) {
                    q.next = q.next.next;
                    return;
                }
                i++;
            }
        } else {
            System.out.println("do nothing");
        }
    }
}
