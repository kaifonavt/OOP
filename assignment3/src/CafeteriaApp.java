import java.awt.*;
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
    public void printAllMenuItems() {
           if(items.isEmpty()){
               System.out.println("No items in the Menu");
               return;
           }
           for ( MenuItem item : items ) {
               System.out.println(item) ;
           }
    }
    public void searchPosition() {
        System.out.println("Enter a Position: ");
        String search = scanner.nextLine().toLowerCase();

        for(MenuItem item : items) {
            if (item.getName().toLowerCase().contains(search)){
                System.out.println(item) ;
            }
        }
    }
    public void addOrderItem() {
        System.out.print("Enter menu item ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        MenuItem item = menuService.getById(id);
        if (item == null || !item.isAvailable()) {
            System.out.println("Item not available");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        currentOrder.put(item, currentOrder.getOrDefault(item, 0) + quantity);
        System.out.println("Added to order");
    }
    private void editOrderItem() {
        System.out.print("Enter menu item ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        MenuItem item = menuService.getById(id);
        if (!currentOrder.containsKey(item)) {
            System.out.println("Item not in order");
            return;
        }

        System.out.print("Enter new quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        currentOrder.put(item, quantity);
        System.out.println("Order updated");
    }
    private void deleteOrderItem() {
        System.out.print("Enter menu item ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        MenuItem item = menuService.getById(id);
        currentOrder.remove(item);
        System.out.println("Item removed");
    }
    private void finishOrder() {
        if (currentOrder.isEmpty()) {
            System.out.println("Your order is empty");
            return;
        }

        int orderId = orderService.createOrder(currentOrder);
        currentOrder.clear();

        System.out.println("Order completed. Your order ID: " + orderId);
    }

}
