public class MenuItem implements MenuService{
    private int id;
    private static int idGen;
    private String name;
    private String description;
    private double price;
    private int quantity;
    public MenuItem(String name, String description, double price, int quantity) {
        id = idGen++;
        setName(name);
        setDescription(description);
        setPrice(price);
        setQuantity(quantity);
    }
    public void setName(String name) {
        if ((name == null)||(name.trim().isEmpty()))
        {throw new IllegalArgumentException("Menu item name cannot be empty");}
        else {this.name = name;}
    }
    public String getName() {return name;}
    public void setDescription(String description) {
        if ((description == null)||(description.trim().isEmpty()))
        {this.description = "No Description";}
        else {this.description = description;}
    }
    public String getDescription() {return description;}
    public void setPrice(double price) {
        if (price < 0)
        {throw new IllegalArgumentException("Price cannot be negative");}
        else {this.price = price;}
    }
    public double getPrice() {return price;}
    public void setQuantity(int quantity) {
        if (quantity < 0)
        {throw new IllegalArgumentException("Quantity cannot be negative");}
        else {this.quantity = quantity;}
    }
    public int getQuantity() {return quantity;}
    public int getId() {return id;}
    @Override
    public String toString() {
        return "MenuItem{id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", quantity=" + quantity + '}';
    }

    @Override
    public void deleteMenuItem() {

    }

    @Override
    public void editMenuItem() {

    }

    @Override
    public void deleteMenuItem(int id) {

    }

    @Override
    public void findMenuItem(String search) {

    }
}
