package day6.sort;

public class Sort {
    int[] selectSort(int[] a) //Simple Selection Sort
    {
//        int a[] = new int[10];
        int i, j, k, n = a.length;
        int min;
        for (i = 0; i < n - 1; i++) {
            min = a[i];
            k = i;
            for (j = i + 1; j < n; j++)
                if (a[j] < min) {
                    k = j;
                    min = a[j];
                }
            if (k != i) swap(a, i, k);
        }
        return a;
    }

    void swap(int[] a, int i, int k) {
        int j = a[i];
        a[i] = a[k];
        a[k] = j;
    }


    int[] insertSort(int[] a) {
        int i, j, x, n = a.length;
        for (i = 1; i < n; i++) {
            x = a[i];
            j = i;
            while (j > 0 && x < a[j - 1]) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = x;
        }
        return a;
    }

    int[] bubbleSort(int[] a) {
        int i, n = a.length;
        boolean swapped;
        do {
            swapped = false;
            for (i = 0; i < n - 1; i++)
                if (a[i] > a[i + 1]) {
                    swap(a, i, i + 1);
                    swapped = true;
                }
        }
        while (swapped);
        return a;
    }

    int[] shellSort(int[] step){
        int h,i,stepnum;
        stepnum = step.length;
        for(i = 0; i <stepnum;i++){
            h = step[i];
            insertShellSort(h,step);
        }
        return step;
    }

    void insertShellSort(int h, int[] a){
        int i,j,x, n = a.length;
        for(i = h; i<n; i++){
            x = a[i]; j = i;
            while (j-h>=0 && x<a[j-h]){
                a[j] = a[j-h]; j=j-h;
                a[j] = x;
            }
        }
    }






    public static void main(String[] args) {
        int[] a = {9, 3, 3, 1, 4, 5, 1, 2, 6, 2, 3, 6, 8, 7, 5, 6, 4};
        Sort sort = new Sort();
        a = sort.shellSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
