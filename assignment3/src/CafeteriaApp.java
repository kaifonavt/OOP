import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CafeteriaApp {
    private List<MenuItem> items = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("Welcome to Cafeteria App!");
            System.out.println("1. Print all menu");
            System.out.println("2. Search position by title");
            System.out.println("3. Add position to order");
            System.out.println("4. Edit position to order");
            System.out.println("5. Delete position from order");
            System.out.println("6. Finish the order");
            System.out.println("7. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> printAllMenuItems();
                case 2 -> searchPosition();
                case 3 -> addOrderItem();
                case 4 -> editOrderItem();
                case 5 -> deliteOrderItem();
                case 6 -> finishOrder();
                case 7 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }
}
