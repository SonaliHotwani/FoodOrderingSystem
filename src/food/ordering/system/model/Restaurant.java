package food.ordering.system.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Restaurant {
    int Id;
    String name;
    List<Item> menu;
    Map<Item, Integer> itemsQuantity;
    float rating;
    int maxOrders;

    public Restaurant(int id, String name, List<Item> menu, Map<Item, Integer> itemsQuantity, float rating, int maxOrders) {
        Id = id;
        this.name = name;
        this.menu = menu;
        this.itemsQuantity = itemsQuantity;
        this.rating = rating;
        this.maxOrders = maxOrders;
    }

    public void updateMenu(Operation operation, List<Item> items) {
        switch (operation) {
            case ADD_NEW: menu.addAll(items);
            break;
            case UPDATE_EXISTING: {
                for (Item currentItem : items) {
                    for (Item item : menu) {
                        if (item.getName().equals(currentItem.getName())) {
                            item.setPrice(currentItem.getPrice());
                        }
                    }
                }
            }
            break;
        }
    }

    public List<Item> getMenu() {
        return menu;
    }

    public Map<Item, Integer> getItemsQuantity() {
        return itemsQuantity;
    }

    public float getRating() {
        return rating;
    }

    public int getMaxOrders() {
        return maxOrders;
    }

    public String getName() {
        return name;
    }
}
