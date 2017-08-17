package day3.bookingticket;

public class SLL<E extends Comparable<? super E>> {
    SLLNode<E> head = null;
    SLLNode<E> tail = null;

    public boolean isEmpty() {
        return head == null;
    }

    public void visit(SLLNode<E> p) {
        System.out.print("-->" + p.info);
    }

    public void print() {
        for (SLLNode<E> p = head; p != null; p = p.next) {
            visit(p);
        }
    }

    // them node vao dau danh sach
    public void addToHead(E info) {
        if (isEmpty()) {
            head = tail = new SLLNode<E>(info);
        } else {
            head = new SLLNode<E>(info, head);
        }
    }

    public void addToTail(E info) {
        if (isEmpty()) {
            head = tail = new SLLNode<E>(info);
        } else {
            tail.next = new SLLNode<E>(info);
            tail = tail.next;
        }
    }

    public int countNode() {
        int count = 0;
        SLLNode<E> p = head;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }

    // Ham chen node van giu thu tu tang cua danh sach goc
    public void addAsc(E info) {
        // p is ghost Node
        SLLNode<E> t, p = new SLLNode<E>(null, head);
        for (t = p; t.next != null && t.next.info.compareTo(info) < 0; t = t.next) {
        }
        ;
        t.next = new SLLNode<E>(info, t.next);
        if (tail == t) {
            tail = t.next; // update tail
        }
        head = p.next; // recovery head
    }

    //ham chen node van giu thu tu tang cua danh sach goc
    public void AddAsc(E info) {
        //p is ghost node
        SLLNode<E> t, p = new SLLNode<E>(null, head);
        for (t = p; t.next != null && t.next.info.compareTo(info) < 0; t = t.next) {
        }
        ;
        t.next = new SLLNode<E>(info, t.next);
        if (tail == t) {
            tail = t.next; //undate tail
        }
        head = p.next; //recovery head
    }

    //ham xoa node dau
    public E deleteFromHead() {
        if (!isEmpty()) {
            E info = head.info;
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
                return info;
            }
        }
        return null;
    }

    public E deleteFromTail() {
        if (!isEmpty()) {
            E info = head.info;
            if (head == tail) {
                head = tail = null;
            } else {
                SLLNode<E> pre = head;
                SLLNode<E> temp = pre.next;
                while (temp.next != null) {
                    pre = pre.next;
                    temp = temp.next;
                }
                tail = pre;
                pre.next = null;
                return tail.info;
            }
        }
        return null;
    }

    public E delete(E info) {
        if (!isEmpty()) {
            if (head == tail && info == head.info) {
                head = tail = null;
            } else {
                SLLNode<E> pre = head;
                SLLNode<E> temp = pre.next;
                while (temp.next != null && temp.info != info) {
                    pre = pre.next;
                    temp = temp.next;
                }
                pre.next = temp.next;
                temp = null;
                return pre.next.info;
            }
        }
        return null;
    }

    public E deleteAt(int i) {
        E info = null;
        if (!isEmpty()) {
            if (head == tail && i == 0) {
                head = tail = null;
            } else if (i == 0) {
                head = head.next;
            } else {
                SLLNode<E> pred;
                int t;
                for (pred = head, t = 1; pred != tail && t < i; pred = pred.next, t++) {
                }
                if (t == i) {
                    info = pred.next.info;
                    pred.next = pred.next.next;
                    if (pred.next == tail) {
                        tail = pred;
                    }
                }
            }
        }
        return info;
    }

    //ham find theo info
    public SLLNode<E> find(E info) {
        for (SLLNode<E> p = head; p != null; p = p.next) {
            if(p.info.compareTo(info) == 0){
                return p;
            }
        }
        return null;
    }
}
