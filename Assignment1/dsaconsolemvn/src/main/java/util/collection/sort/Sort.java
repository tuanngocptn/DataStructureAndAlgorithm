package util.collection.sort;

import util.collection.LinkedLstDequeue;

public interface Sort<E> {
    boolean compare(E o,E o1);
    LinkedLstDequeue<E> sort(LinkedLstDequeue<E> o);
}
