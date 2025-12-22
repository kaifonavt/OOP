import javax.management.openmbean.TabularDataSupport;
import java.util.ArrayList;
import java.util.Scanner;

public class FleetApp {
    private ArrayList<Vehicle> vehicles;
    private Scanner scanner = new Scanner(System.in);
    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("Fleet Management System");
            System.out.println("1. Print all vehicles");
            System.out.println("2. Add new car");
            System.out.println("3. Add new bus");
            System.out.println("4. Show total yearly insurance fees");
            System.out.println("5. Show vehicles older than N years");
            System.out.println("6. Perform service for all vehicles");
            System.out.println("7. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> printAllVehicle();
                case 2 -> addCar();
                case 3 -> addBus();
                case 4 -> showInsuranceFees();
                case 5 -> showVehiclesOlderThanN();
                case 6 -> performService();
                case 7 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }
    public void printAllVehicle() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet");
            return;
        }
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }
    }
    public void addCar() {
        try {
            System.out.print("Model: ");
            String model = scanner.nextLine();
            System.out.print("Price: ");
            double price = scanner.nextDouble();
            System.out.print("Year: ");
            int year = scanner.nextInt();
            System.out.print("Number of doors: ");
            int numberOfDoors = scanner.nextInt();
            scanner.nextLine();
            vehicles.add(new Car(model, year, price, numberOfDoors));
            System.out.println("Car added successfully");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void addBus() {
        try {
            System.out.print("Model: ");
            String model = scanner.nextLine();
            System.out.print("Price: ");
            double price = scanner.nextDouble();
            System.out.print("Year: ");
            int year = scanner.nextInt();
            System.out.print("Capacity of passengers: ");
            int passengerCapacity = scanner.nextInt();
            scanner.nextLine();
            vehicles.add(new Bus(model, year, price, passengerCapacity));
            System.out.println("Book added successfully");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void showInsuranceFees() {
        double sum = 0;
        for (Vehicle vehicle : vehicles) {
            sum += vehicle.calculateInsuranceFee();
        }
        System.out.println("Insurance fees: " + sum);
    }
    public void showVehiclesOlderThanN() {
        System.out.print("Nth year: ");
        int nAge = scanner.nextInt();
        for (Vehicle vehicle : vehicles) {
            if (nAge < vehicle.getAge(2025)) {
                System.out.println(vehicle.toString());
            }
        }
    }
    public void performService() {
        for (Vehicle vehicle : vehicles) {
            vehicle.performService();
        }
    }
}