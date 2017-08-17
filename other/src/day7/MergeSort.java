package day7;

public class MergeSort {
    private int[] a;

    public MergeSort(int[] a) {
        this.a = a;
    }

    void mergeSort(int p, int r) {
        if (p >= r) return;
        int q = (p + r) / 2;
        mergeSort(p, q);
        mergeSort(q + 1, r);
        merge(p, q, r);
    }

    void merge(int p, int q, int r) {
        if (!(p <= q) && (q <= r)) return;
        int n, i, j, k, x;
        n = r - p + 1;
        int[] b = new int[n];
        i = p;
        j = q + 1;
        k = 0;
        while (i <= q && j <= r) {
            if (a[i] < a[j])
                b[k++] = a[i++];
            else
                b[k++] = a[j++];
        }
        while (i <= q) b[k++] = a[i++];
        while (j <= r) b[k++] = a[j++];
        k = 0;
        for (i = p; i <= r; i++) a[i] = b[k++];
    }

    void display(){
        for (int i = 0; i < a.length; i++) {
            System.out.print(" "+a[i]);
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        {int [] b = {7,3,5,9,11,8,6,15,10,12,14};
            MergeSort t = new MergeSort(b);
            t.display();
            int n=b.length;
            t.mergeSort(0,n-1);
            t.display();
            System.out.println();
        }
    }
}

