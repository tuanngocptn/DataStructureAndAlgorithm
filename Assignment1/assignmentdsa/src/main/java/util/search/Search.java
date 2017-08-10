package util.search;

import util.collection.LinkedLstDequeue;

public interface Search<E> {
	E get(E e);
	LinkedLstDequeue<E> searchAll(E e2);
	int compare(E e1,E e2);
	boolean constains(E e1, E e2);
}
