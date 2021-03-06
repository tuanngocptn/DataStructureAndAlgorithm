package util.search;

import util.collection.DoubleLinkedLstQueue;

/**
 *
 * @author : Pham Tuan Ngoc - id : gc01007 - class : bt007
 *
 * the interface define all search
 */
public interface Search<E> {
	E get(E e);
	DoubleLinkedLstQueue<E> searchAll(E e2);
	int compare(E e1,E e2);
	boolean constains(E e1, E e2);
}
