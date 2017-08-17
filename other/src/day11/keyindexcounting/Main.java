package day11.keyindexcounting;

public class Main {
    public static void main(String[] args) {
        Item[] a = new Item[]{
            new Item("Bao", 2),
            new Item("Thinh", 3),
            new Item("Hang", 3),
            new Item("Phat", 4),
            new Item("Hieu", 1),
            new Item("Tin", 3),
            new Item("Ngoc", 2),
            new Item("Toan", 3),
            new Item("Long", 2),
        };
        int N = a.length;
        int R = 5;

        Item[] aux = new Item[N];
        int[] count = new int[R+1];

        //step 1 dem tan xuat xuat hien cua key
        for (int i = 0; i < N; i++) {
            count[a[i].getKey()+1]++;
        }

        //step 2 transform counts to indices
        for (int r = 0; r < R; r++) {
            count[r+1]+=count[r];
        }

        //step 3 phan phoi data vao mang indices
        for (int i = 0; i < N; i++) {
            aux[count[a[i].getKey()]++]=a[i];
        }

        //step 5 copy back
        System.arraycopy(aux,0,a,0,N);

        //step 6 print
        for (int i = 0; i < N;i++) {
            System.out.println(" "+ a[i]);
        }
        System.out.println("");
    }
}
