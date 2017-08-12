package util.collection.sort;

import util.collection.LinkedLstDequeue;

/**
 *
 * @author : Pham Tuan Ngoc
 *
 * the interface to define structure all of sort
 */
public interface Sort<E> {
    boolean compare(E o,E o1);
    LinkedLstDequeue<E> sort(LinkedLstDequeue<E> o);
}
