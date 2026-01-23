import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CafeteriaApp {
    private List<MenuItem> menuItems = new ArrayList<>();
    private List<OrderItem> orderItems = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    public void run() {
        boolean running = true;
        orders.add(new Order());
        while (running) {
            System.out.println("Welcome to Cafeteria App!");
            System.out.println("1. Print all menu");
            System.out.println("2. Print all order items");
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
                case 2 -> printAllOrderItems();
                case 3 -> addOrderItem();
                case 4 -> editOrderItem();
                case 5 -> deleteOrderItem();
                case 6 -> finishOrder();
                case 7 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }
    public void printAllMenuItems() {
           if(menuItems.isEmpty()){
               System.out.println("No items in the Menu");
               return;
           }
           for ( MenuItem item : menuItems ) {
               System.out.println(item) ;
           }
    }
    public void printAllOrderItems() {
        if(orderItems.isEmpty()){
            System.out.println("No items in the Order");
            return;
        }
        for ( OrderItem item : orderItems ) {
            System.out.println(item) ;
        }
    }
    public void addOrderItem() {
        printAllMenuItems();
        System.out.print("Enter menu item ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (menuItems.get(id) == null || menuItems.get(id).getQuantity()==0) {
            System.out.println("Item not available");
            return;
        }
        OrderItem currentItem = new OrderItem();
        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        orderItems.add(currentItem);
        System.out.println("Added to order");
    }
    private void editOrderItem() {
        printAllOrderItems();
        System.out.print("Enter order item ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (orderItems.get(id) == null) {
            System.out.println("Item not in order");
            return;
        }
        System.out.print("Enter new quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        orderItems.get(id).setQuantity(quantity);
        System.out.println("Order updated");
    }
    private void deleteOrderItem() {
        printAllOrderItems();
        System.out.print("Enter order item ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        orderItems.remove(id);
        System.out.println("Item removed");
    }
    private void finishOrder() {
        if (orderItems.isEmpty()) {
            System.out.println("Your order is empty");
            return;
        }
        System.out.print("Enter user ID: ");
        int userId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter delivery address: ");
        String address = scanner.nextLine();
        //saving ot database
        orderItems.clear();
        System.out.println("Order completed. " + order.toString());
    }
}
