package day3.bookingticket;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu().add("Create new flight","Booking ticket","Cancel ticket","Find customer","Quit");
        Manager manager = new Manager();
        while (true){
            int c = menu.show();
            switch (c){
                case 1: manager.create();break;
                case 2: manager.booking();break;
                case 3: manager.cancel();break;
                case 4: manager.search();break;
                case 5: System.out.println("Viet name Airline bye");break;
            }
        }
    }
}
