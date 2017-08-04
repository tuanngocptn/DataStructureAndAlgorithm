package collection;

import com.google.gson.Gson;

public class LinkedLstQueue<E> {
    LinkedLst head;
    LinkedLst tail;
    LinkedLst current;

    class LinkedLst {
        E e;
        LinkedLst next;

        private LinkedLst() {
        }

        private LinkedLst(E e, LinkedLst next) {
            this.e = e;
            this.next = next;
        }
    }

    public LinkedLstQueue() {
        this.head = this.tail = this.current = null;
    }

    public void push(E value) {
        LinkedLst linkedLst = new LinkedLst(value,null);
        if (isEmpty()) {
            linkedLst.next = head;
            head = linkedLst;
            tail = head;
        } else {
            tail.next = linkedLst;
            tail = linkedLst;
        }
    }

    public boolean hasNext() {
        if (isEmpty()) return false;
        else if (current.next != null) return true;
        return false;
    }

    public boolean isEmpty() {
        return head == null || tail == null;
    }

    public E pop() {
        E value = head.e;
        head = head.next;
        return value;
    }

    public E peek() {
        current = head;
        return current.e;
    }

    public String display() {
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
}
