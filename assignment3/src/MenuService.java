public interface MenuService {
    void editMenuItem(String name, String description, double price, int quantity);
    void findMenuItem(String search);
}
