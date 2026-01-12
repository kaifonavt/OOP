public class Customer {
    private int id;
    private static int idGen;
    private String name;
    private String address;
    private String email;

    public Customer(int id, String name, String address, String email) {
        this.id = idGen++;
        setName(name);
        setAddress(address);
        setEmail(email);
    }
    public int getId() {return id;}
    public String getName() {return name;}
    public void setName(String name) {
        if ((name == null)||(name.trim().isEmpty()))
        {throw new IllegalArgumentException("Menu item name cannot be empty");}
        else {this.name = name;}
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
