package day7;

public class HeapSort {
    int []a;
    int n;

    public HeapSort(int[] a, int n) {
        this.a = a;
        this.n = n;
    }

    void heapSort() { //Transform the array to HEAP
        int i, s, f;
        int x;
        for (i = 1; i < n; i++) {
            x = a[i];
            s = i; //s is a son, f=(s-1)/2 is father
            while (s > 0 && x > a[(s - 1) / 2]) {
                a[s] = a[(s - 1) / 2];
                s = (s - 1) / 2;
            }
            a[s] = x;
        }

// Transform heap to sorted array
        for (i = n - 1; i > 0; i--) {
            x = a[i];
            a[i] = a[0];
            f = 0; //f is father
            s = 2 * f + 1; //s is a left son
// if the right son is larger then it is selected
            if (s + 1 < i && a[s] < a[s + 1]) s = s + 1;
            while (s < i && x < a[s]) {
                a[f] = a[s];
                f = s;
                s = 2 * f + 1;
                if (s + 1 < i && a[s] < a[s + 1]) s = s + 1;
            }
            a[f] = x;
        }
    }
    void print(){
        for (int i = 0; i < a.length; i++) {
            System.out.print(" "+a[i]);
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int []a = {1,9,2,8,3,7,4,6,5};
        HeapSort heapSort = new HeapSort(a,a.length);
        heapSort.print();
        heapSort.heapSort();
        heapSort.print();
    }

}
