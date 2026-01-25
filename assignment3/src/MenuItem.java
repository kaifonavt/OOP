public class MenuItem {
    private int id;
    private static int idGen = 1;
    private String name;
    private String description;
    private double price;
    private int quantity;

    public MenuItem(String name, String description, double price, int quantity) {
        this.id = idGen++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {return name;}

    public String getDescription() {return description;}

    public double getPrice() {return price;}

    public int getQuantity() {return quantity;}
    public int getId() {return id;}
    public void reduceQuantity(int amount) {
        if (amount > quantity)
            throw new IllegalArgumentException("Not enough stock");
        quantity -= amount;
    }

    @Override
    public String toString() {
        return "MenuItem{id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price + // Removed extra single quotes here
                ", quantity=" + quantity + '}';
    }

}
