package day7;

public class QuickSort {
    void QuickSort(int[] a, int left, int right) {
        int index = partion(a, left, right);
        if (left < index - 1) {
            QuickSort(a, left, index - 1);
        }
        if (index < right) {
            QuickSort(a, index, right);
        }
    }

    private int partion(int[] a, int left, int right) {
        int i = left, j = right;
        int tmp;
        int pivot = a[(left + right) / 2];
        while (i <= j) {
            if(a[i] == pivot || a[j] == pivot){
                i ++;
                j --;
            }
            while (a[i] < pivot) {
                i++;
            }
            while (a[j] > pivot) {
                j--;
            }
            if (i <= j) {
                tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                i++;
            }
        }
        return i;
    }

    public void printResult(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        QuickSort q = new QuickSort();
        System.out.println("\nOriginal Array");
        int[] a = {1,9,2,8,3,7,4,6,5};
        q.printResult(a);
        System.out.println("\nArray after quick sort");
        q.QuickSort(a, 0, a.length - 1);
        q.printResult(a);
        System.out.println("");
    }
}
