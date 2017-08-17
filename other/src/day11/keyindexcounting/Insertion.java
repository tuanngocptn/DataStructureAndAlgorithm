package day11.keyindexcounting;

public class Insertion {
    private static boolean less(String v, String w, int d){
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

    private static void exch(String[] a, int i1, int i2){
        String temp = a[i1];
        a[i1] = a[i2];
        a[i2] = temp;
    }

    public static void sort(String[] a, int lo, int hi, int d){
        for (int i = lo; i <= hi; i++) {
            for (int j = 0; j > lo && less(a[j],a[j-1],d); j--) {
                exch(a,j,j-1);
            }
        }
    }
}
