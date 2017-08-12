package util.collection.sort.impl;

import util.collection.LinkedLstDequeue;
import util.collection.sort.Sort;

/**
 *
 * @author : Pham Tuan Ngoc
 *
 * define the select sort
 */
public abstract class SelectSort<E> implements Sort {

    /**
     * method sort
     * @param linkedLstDequeue list before sort
     * @return result list after sort
     */
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

    /**
     * swap 2 element in list with i and j index
     * @param linkedLstDequeue list before
     * @param i index
     * @param j index
     */
    private void swap(LinkedLstDequeue<E> linkedLstDequeue, int i, int j) {
        E e = linkedLstDequeue.getAt(i);
        E e1 = linkedLstDequeue.getAt(j);
        linkedLstDequeue.delete(i);
        linkedLstDequeue.insertAt(i, e1);
        linkedLstDequeue.delete(j);
        linkedLstDequeue.insertAt(j, e);
    }
}
