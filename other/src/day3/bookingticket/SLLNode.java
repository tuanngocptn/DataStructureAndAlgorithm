package day3.bookingticket;

public class SLLNode <E>{
    protected E info;
    protected SLLNode<E> next;
    public SLLNode(){

    }

    public SLLNode(E info, SLLNode<E> next) {
        this.info = info;
        this.next = next;
    }

    public SLLNode(E info) {
        this.info = info;
    }
}
