package util.sort;

import util.collection.DoubleLinkedLstQueue;

/**
 *
 * @author : Pham Tuan Ngoc
 *
 * the interface to define structure all of sort
 */
public interface Sort<E> {
    boolean compare(E o,E o1);
    DoubleLinkedLstQueue<E> sort(DoubleLinkedLstQueue<E> o);
}
