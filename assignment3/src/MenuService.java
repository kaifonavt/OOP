import java.util.ArrayList;
import java.util.List;

public class MenuService {
    private List<MenuItem> menuItems = new ArrayList<>();

    public void add(MenuItem item) {
        menuItems.add(item);
    }

    public List<MenuItem> getAll() {
        return menuItems;
    }

    public MenuItem getById(int id) {
        return menuItems.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Menu item not found"));
    }
}
