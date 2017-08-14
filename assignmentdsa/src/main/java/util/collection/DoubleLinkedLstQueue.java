package util.collection;

import com.google.gson.Gson;

public class DoubleLinkedLstQueue<E> {

    private LinkedLst head = null;
    private LinkedLst tail = null;
    private LinkedLst current = null;

    class LinkedLst {
        E e;
        LinkedLst next;
        LinkedLst previous;

        private LinkedLst() {
        }

        private LinkedLst(E e, LinkedLst next, LinkedLst previous) {
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
        LinkedLst linkedLst = head;
        int sum = 0;
        while (linkedLst != null) {
            sum++;
            linkedLst = linkedLst.next;
        }
        return sum;
    }

    public String displayForward() {
        if (!isEmpty()) {
            LinkedLst linkedLst = head;
            StringBuilder result = new StringBuilder();
            Gson gson = new Gson();
            result = result.append(gson.toJson(linkedLst.e));
            linkedLst = linkedLst.next;
            while (linkedLst != null) {
                result.append(",");
                result = result.append(gson.toJson(linkedLst.e));
                linkedLst = linkedLst.next;
            }
            return String.format("%s%s%s", "[", result.toString(), "]");
        }
        return "[]";
    }

    public String displayBackward() {
        if (!isEmpty()) {
            LinkedLst linkedLst = tail;
            StringBuilder result = new StringBuilder();
            Gson gson = new Gson();
            result = result.append(gson.toJson(linkedLst.e));
            linkedLst = linkedLst.previous;
            while (linkedLst != null) {
                result.append(",");
                result = result.append(gson.toJson(linkedLst.e));
                linkedLst = linkedLst.previous;
            }
            return String.format("%s%s%s", "[", result.toString(), "]");
        }
        return "[]";
    }

    public void insertFirst(E value) {
        if (isEmpty()) {
            LinkedLst linkedLst = new LinkedLst(value, null, null);
            head = linkedLst;
            tail = linkedLst;
        } else {
            LinkedLst linkedLst = new LinkedLst(value, head, null);
            head.previous = linkedLst;
            head = linkedLst;
        }
    }

    public void insertLast(E value) {
        if (isEmpty()) {
            LinkedLst linkedLst = new LinkedLst(value, null, null);
            head = linkedLst;
            tail = linkedLst;
        } else {
            LinkedLst linkedLst = new LinkedLst(value, null, tail);
            tail.next = linkedLst;
            tail = linkedLst;
        }
    }

    public E deleteFirst() {
        LinkedLst linkedLst = head;
        if (length() > 1) {
            head = head.next;
            head.previous = null;
        } else {
            head = null;
        }
        return linkedLst.e;
    }

    public E deleteLast() {
        LinkedLst linkedLst = tail;
        tail = tail.previous;
        tail.next = null;
        return linkedLst.e;
    }

    public E delete(int location) {
        if (location < 1) deleteFirst();
        else if (location > length() - 2) deleteLast();
        else {
            LinkedLst linkedLst = head;
            for (int i = 0; i < location - 1; i++) {
                linkedLst = linkedLst.next;
            }
            LinkedLst linkedLstDel = linkedLst.next;
            LinkedLst linkedLstAft = linkedLstDel.next;
            linkedLst.next = linkedLstAft;
            return linkedLstDel.e;
        }
        return null;
    }

    public void insertAt(int location, E value) {
        if (location < 1) insertFirst(value);
        else if (location > length() - 1) insertLast(value);
        else {
            LinkedLst linkedLst = head;
            for (int i = 0; i < location - 1; i++) {
                linkedLst = linkedLst.next;
            }
            LinkedLst linkedLstNew = new LinkedLst(value, linkedLst.next, linkedLst);
            linkedLst.next.previous = linkedLstNew;
            linkedLst.next = linkedLstNew;
        }
    }

    public E getAt(int location) {
        if(location < 1) return head.e;
        if(location > length() - 2) return tail.e;
        LinkedLst linkedLst = head;
        for (int i = 0; i < location; i++) {
            linkedLst = linkedLst.next;
        }
        return linkedLst.e;
    }
}
