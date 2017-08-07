package util.collection.sort;

import util.collection.LinkedLstDequeue;

public abstract class SelectSort<E> implements Sort {
    public LinkedLstDequeue sort(LinkedLstDequeue linkedLstDequeue) {
        for (int i = 0; i < linkedLstDequeue.length() - 1; i++) {
            for (int j = i; j < linkedLstDequeue.length(); j++) {
                if (compare(linkedLstDequeue.getAt(i), linkedLstDequeue.getAt(j))) {
                    swap(linkedLstDequeue, i, j);
                }
            }

        }
        return linkedLstDequeue;
    }

    private void swap(LinkedLstDequeue<E> linkedLstDequeue, int i, int j) {
        E e = linkedLstDequeue.getAt(i);
        E e1 = linkedLstDequeue.getAt(j);
        linkedLstDequeue.delete(i);
        linkedLstDequeue.insertAt(i, e1);
        linkedLstDequeue.delete(j);
        linkedLstDequeue.insertAt(j, e);
    }
}
