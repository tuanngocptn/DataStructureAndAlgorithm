package day11.keyindexcounting;

public class MSDDemo {
    public static void main(String[] args) {
        //Data
        String[] a = {"she","sells","seashells","by","the","sea","shore","the","shells","she","sells","surely","seashells"};
        //sort
        MSD.sort(a);
        for(String a1:a){
            System.out.println(a1);
        }
    }
}
