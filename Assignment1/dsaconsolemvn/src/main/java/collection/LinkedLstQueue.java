package collection;

import com.google.gson.Gson;

public class LinkedLstQueue<E> {
    LinkedLst head = null;
    LinkedLst tail = null;
    LinkedLst current = null;

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
    }

    public void push(E value) {
        LinkedLst linkedLst = new LinkedLst(value,null);
        if (isEmpty()) {
            linkedLst.next = head;
            tail = head = linkedLst;
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
}
