package day5.recur;

public class Main {
    static int sum(int n) {
        if (n == 0) return 0;
        else return n + sum(n - 1);
    }

    static int sum(int[] a, int n) {
        if (n == 0) return 0;
        else return sum(a, n - 1) + a[n - 1];
    }

    static int sumSquare(int n) {
        if (n == 0) return 0;
        else return n * n + sumSquare(n - 1);
    }

    static int findMax(int[] a, int n) {
        if (n == 0) return a[0];
        if (a[n - 1] > findMax(a, n - 1)) return a[n - 1];
        return findMax(a, n - 1);
    }

    static String decToBina(int dema) {
        if (dema == 0) return "0";
        return decToBina(dema / 2) + Integer.toString(dema % 2);
    }

    static String toBina(int n) {
        String s = "";
        if (n % 2 == 0) s = "0";
        else s = "1";
        if (n < 2) return s;
        return toBina(n / 2) + s;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(sum(a, a.length));

        System.out.println(sumSquare(5));

        System.out.println(findMax(a, a.length));

        System.out.println(decToBina(50));


    }
}
