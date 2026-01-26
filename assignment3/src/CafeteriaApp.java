import java.util.*;

public class CafeteriaApp {

    private MenuService menuService = new MenuService();
    private OrderService orderService = new OrderService();
    private PaymentService paymentService = new PaymentService();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        // Add some initial menu items for demo purposes
        menuService.add(new MenuItem("Coffee", "Hot brewed coffee", 2.50, 50));
        menuService.add(new MenuItem("Pastry", "Fresh croissant", 3.00, 30));

        while (true) {
            System.out.println("\n--- Cafeteria Menu ---");
            System.out.println("""
                1. Add menu item
                2. View menu
                3. Place order
                4. View active orders
                5. Pay order
                6. Exit
                """);
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addMenuItem();
                    case 2 -> menuService.getAll().forEach(System.out::println);
                    case 3 -> placeOrder();
                    case 4 -> orderService.getActiveOrders().forEach(System.out::println);
                    case 5 -> payOrder();
                    case 6 -> System.exit(0);
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private void addMenuItem() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String desc = scanner.nextLine();

        try {
            System.out.print("Price: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Quantity: ");
            int qty = Integer.parseInt(scanner.nextLine());

            menuService.add(new MenuItem(name, desc, price, qty));
            System.out.println("Menu item added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number for price and quantity.");
        }
    }

    private void placeOrder() {
        System.out.print("Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        List<OrderItem> items = new ArrayList<>();

        while (true) {
            menuService.getAll().forEach(System.out::println);
            System.out.print("Menu ID (0 to finish): ");
            int id = scanner.nextInt();
            if (id == 0) break;

            System.out.print("Quantity: ");
            int qty = scanner.nextInt();
            scanner.nextLine();

            try {
                MenuItem item = menuService.getById(id);
                item.reduceQuantity(qty);
                items.add(new OrderItem(id, item.getName(), qty, item.getPrice()));
            } catch (RuntimeException e) {
                System.out.println("Error placing order item: " + e.getMessage());
            }
        }

        try {
            Order order = orderService.placeOrder(customerId, items);
            System.out.println("Order placed: " + order);
        } catch (RuntimeException e) {
            System.out.println("Could not place order: " + e.getMessage());
        }
    }

    private void payOrder() {
        System.out.print("Order ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Order order = orderService.getById(id);
            paymentService.processPayment(order);
        } catch (RuntimeException e) {
            System.out.println("Payment failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new CafeteriaApp().run();
    }
}


