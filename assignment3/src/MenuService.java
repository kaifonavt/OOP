public interface MenuService {
    void getById(int id);
    void editMenuItem(String name, String description, double price, int quantity);
    void findMenuItem(String search);
}
