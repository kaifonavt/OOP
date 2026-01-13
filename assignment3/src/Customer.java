public class Customer {
    private int id;
    private static int idGen;
    private String name;
    private String email;

    public Customer(int id, String name, String address, String email) {
        this.id = idGen++;
        setName(name);
        setEmail(email);
    }
    public int getId() {return id;}
    public String getName() {return name;}
    public void setName(String name) {
        if ((name == null)||(name.trim().isEmpty()))
        {throw new IllegalArgumentException("Menu item name cannot be empty");}
        else {this.name = name;}
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if ((name == null)||(name.trim().isEmpty()))
        {throw new IllegalArgumentException("Menu item name cannot be empty");}}
}
