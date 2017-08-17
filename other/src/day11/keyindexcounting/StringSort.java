package day11.keyindexcounting;

public class StringSort {
    public static void main(String[] args) {
        String[] a = {"4PGC938", "3CI0720", "1ICJ550", "10HV845", "4JZI524", "1ICK750", "3CI0720", "1OHV845", "10HV845", "2RLA629", "2RLA629", "3ATW723"};
        sort(a, 7);
        for (String a1 : a) {
            System.out.println(" " + a1);
        }
        System.out.println("");
    }

    public static void sort(String[] a, int w) {
        int R = 256;
        int N = a.length;
        for (int d = w - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }
            String aux[] = new String[N];
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]] = a[i];
                count[a[i].charAt(d)]++;
            }
            System.arraycopy(aux, 0, a, 0, N);
        }
    }
}
