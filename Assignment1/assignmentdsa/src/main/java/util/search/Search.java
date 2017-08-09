package util.search;

public interface Search<E> {
	E get(E e);
	int compare(E e1,E e2);
}
