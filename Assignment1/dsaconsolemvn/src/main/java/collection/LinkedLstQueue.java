package collection;

public class LinkedLstQueue<E> {
    LinkedLst head;
    LinkedLst tail;
    LinkedLst current;

    class LinkedLst<E> {
        E e;
        LinkedLst next;

        public LinkedLst() {
        }

        public LinkedLst(E e, LinkedLst next) {
            this.e = e;
            this.next = next;
        }
    }

    public LinkedLstQueue() {
        this.head = this.tail = this.current = null;
    }

    public void add(E e) {
        LinkedLst linkedLst = new LinkedLst(e,head);
        if (head == null || tail == null) {
            head = linkedLst;
            tail = head;
        } else {
            head = linkedLst;
        }
    }

    public boolean hasNext() {
        if (head == null) {
            return false;
        }
        if (current == null) {
            current = head;
            return true;
        } else if (current.next != null) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        LinkedLst linkedLst = head;
        while (linkedLst != null) {
            System.out.print(linkedLst.e.toString() + " ");
            linkedLst = linkedLst.next;
        }
        return "";
    }
}
