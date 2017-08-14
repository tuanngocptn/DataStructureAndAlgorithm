package util.collection.sort.impl;

import util.collection.DoubleLinkedLstQueue;
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
     * @param doubleLinkedLstQueue list before sort
     * @return result list after sort
     */
    public DoubleLinkedLstQueue sort(DoubleLinkedLstQueue doubleLinkedLstQueue) {
        for (int i = 0; i < doubleLinkedLstQueue.length() - 1; i++) {
            for (int j = i; j < doubleLinkedLstQueue.length(); j++) {
                if (compare(doubleLinkedLstQueue.getAt(i), doubleLinkedLstQueue.getAt(j))) {
                    swap(doubleLinkedLstQueue, i, j);
                }
            }
        }
        return doubleLinkedLstQueue;
    }

    /**
     * swap 2 element in list with i and j index
     * @param doubleLinkedLstQueue list before
     * @param i index
     * @param j index
     */
    private void swap(DoubleLinkedLstQueue<E> doubleLinkedLstQueue, int i, int j) {
        E e = doubleLinkedLstQueue.getAt(i);
        E e1 = doubleLinkedLstQueue.getAt(j);
        doubleLinkedLstQueue.delete(i);
        doubleLinkedLstQueue.insertAt(i, e1);
        doubleLinkedLstQueue.delete(j);
        doubleLinkedLstQueue.insertAt(j, e);
    }
}
