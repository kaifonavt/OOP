import com.cafeteria.billing.TaxConfig;
import com.cafeteria.ordering.*;
import com.cafeteria.infrastructure.IDB;
import com.cafeteria.infrastructure.DatabaseConnection;
import com.cafeteria.menumanagement.MenuItem;
import com.cafeteria.menumanagement.IMenuItemRepository;
import com.cafeteria.menumanagement.MenuItemRepository;
import com.cafeteria.menumanagement.MenuService;
import com.cafeteria.billing.PaymentService;
import com.cafeteria.billing.Result;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CafeteriaApp {
    private IMenuItemRepository menuRepo;
    private IOrderRepository orderRepo;
    private MenuService menuService;
    private OrderService orderService;
    private Scanner scanner;
    private PaymentService paymentService;


    public CafeteriaApp() {
        IDB db = (IDB) DatabaseConnection.getInstance();
        this.menuRepo = new MenuItemRepository(db);
        this.orderRepo = new OrderRepository(db);
        this.menuService = new MenuService(menuRepo);
        this.orderService = new OrderService(menuRepo, orderRepo);
        this.scanner = new Scanner(System.in);
        this.paymentService = new PaymentService();
    }

    public void run() throws SQLException {
        System.out.println("Welcome to Cafeteria!");

        while (true) {
            System.out.println("\n1. Add Menu item");
            System.out.println("2. Find by ID");
            System.out.println("3. Place an order");
            System.out.println("4. View active orders");
            System.out.println("5. Mark order as completed");
            System.out.println("6. View menu");
            System.out.println("7. Pay for order");
            System.out.print("8. Quit");
            System.out.print("\nChoose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addMenuItem();
                    break;
                case 2:
                    findMenuItem();
                    break;
                case 3:
                    placeOrder();
                    break;
                case 4:
                    viewActiveOrders();
                    break;
                case 5:
                    markOrderCompleted();
                    break;
                case 6:
                    viewMenu();
                    break;
                case 7:
                    payForOrder();
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void addMenuItem() {
        try {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Description: ");
            String desc = scanner.nextLine();
            System.out.print("Price: ");
            Double price = scanner.nextDouble();
            System.out.print("Quantity: ");
            Integer qty = scanner.nextInt();
            scanner.nextLine();
            menuService.addMenuItem(new MenuItem(name, desc, price, qty));
            System.out.println("Item added!");
        } catch (InvalidInputException e) {
            System.out.println("Validation error: " + e.getMessage());
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void findMenuItem() {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            MenuItem item = menuRepo.getById(id);
            System.out.println(item);

        } catch (SQLException e) {
            System.out.println("Error: Item not found with that ID!");
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Please enter a valid number!");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void placeOrder() {
        try {
            System.out.print("Enter customer ID: ");
            int customerId = getIntInput();
            List<OrderItem> orderItems = new ArrayList<>();
            double subtotal = 0;

            while (true) {
                System.out.println("\n--- Available Menu ---");
                Result<List<MenuItem>> result = menuService.getAllAvailableItems();

                if (result.isSuccess()) {
                    List<MenuItem> availableItems = result.getData();

                for (MenuItem item : availableItems) {
                    System.out.printf("ID: %d | %s - $%.2f (Available: %d)%n",
                            item.getId(), item.getName(), item.getPrice(), item.getQuantity());
                }

                System.out.print("\nEnter menu item ID (0 to finish): ");
                int menuItemId = getIntInput();
                if (menuItemId == 0) break;

                System.out.print("Enter quantity: ");
                int quantity = getIntInput();

                    MenuItem selectedItem = menuService.getMenuItemById(menuItemId);

                    if (selectedItem == null) {
                        System.out.println("Error: Item ID " + menuItemId + " not found!");
                    } else if (selectedItem.getQuantity() < quantity) {
                        System.out.println("Error: Only " + selectedItem.getQuantity() + " left!");
                    } else {
                        orderItems.add(new OrderItem(0, menuItemId, selectedItem.getName(), quantity, selectedItem.getPrice()));
                        subtotal += (selectedItem.getPrice() * quantity);
                        System.out.println("Added to order!");
                    }
                } else {
                    System.out.println("Error: " + result.getMessage());
                    break;
                }
            }
            if (!orderItems.isEmpty()) {
                double finalTotal = TaxConfig.getInstance().calculateTotalWithTax(subtotal);
                System.out.println("\n--- Order Billing Summary ---");
                System.out.printf("Subtotal: $%.2f%n", subtotal);
                System.out.printf("Total with Tax (15%%): $%.2f%n", finalTotal);
                System.out.println("------------------------------");

                System.out.println("\nSelect Delivery Type: 1. Delivery | 2. Pickup | 3. Dine-in");
                int typeChoice = getIntInput();
                String type = (typeChoice ==1) ? "DELIVERY" : (typeChoice ==2) ? "PICKUP" :"DINEIN";

                Order order = orderService.placeOrder(customerId, orderItems);
                order.setTotalPrice(finalTotal);
                System.out.println(" Order placed successfully! Order ID: " + order.getId());
                System.out.println("Final Amount: $" + finalTotal);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewActiveOrders() {
        try {
            List<Order> orders = orderService.getActiveOrders();

            if (orders.isEmpty()) {
                System.out.println("No active orders.");
                return;
            }

            System.out.println("\n=== ACTIVE ORDERS ===");
            for (Order order : orders) {
                System.out.println("Order ID: " + order.getId() +
                        " | Customer ID: " + order.getCustomerId() +
                        " | Completed: " + order.isCompleted());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void markOrderCompleted() {
        try {
            System.out.print("Enter order ID to complete: ");
            int orderId = getIntInput();

            orderService.markOrderAsCompleted(orderId);
            System.out.println("Order marked as completed!");

        } catch (OrderNotFoundException e) {
            System.out.println("Error:" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewMenu() {
        try {
            Result<List<MenuItem>> result = menuService.getAllAvailableItems();

            if (result.isSuccess()) {
                List<MenuItem> items = result.getData();

               System.out.println("\n=== MENU ===");
               for (MenuItem item : items) {
                    System.out.printf("ID: %d | %s - $%.2f%n",
                        item.getId(), item.getName(), item.getPrice());
                    System.out.println("   " + item.getDescription());
                    System.out.println("   Available: " + item.getQuantity());
                    System.out.println("---");
                }
            } else {
                System.out.println("Error: " + result.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private int getIntInput() {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (Exception e) {
                System.out.print("Invalid input! Enter a number: ");
                scanner.nextLine();
            }
        }
    }

    private void payForOrder() {
        try {
            System.out.print("Enter order ID to pay: ");
            int orderId = getIntInput();

            Order order = orderService.getOrderById(orderId);
            paymentService.processPayment(order);
            orderService.markOrderAsCompleted(orderId);
            System.out.println("Order #" + orderId + " paid and completed!");

        } catch (OrderNotFoundException e) {
            System.out.println("Error:" + e.getMessage());
        } catch (SQLException e) {
            System.out.println(" Database error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(" Payment failed:" + e.getMessage());
        }
    }
}


