import food.ordering.system.FeedMe;
import food.ordering.system.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

//    Restaurant 1:
//    Item A: 50
//    Item B: 100
//    Item C: 200
//    Capacity: 2
//
//    Restaurant 2:
//    Item A: 60
//    Item B: 100
//    Capacity: 5
//
//    Restaurant 3:
//    Item C: 210
//    Capacity: 10

//1. U1 : A-2, B-3, C-1, LC
//2. U2 : A-1, B-1, C-2, LC
//3. U1 : C-2, LC
//4. Show all orders for U1 and U2.
//5. Update Restaurant3 item C to 180.
// 6. U2 : C-3, LC
//7. Show all orders for U1 and U2.
//8. Complete 1 order from Rest1.
//9. U1 : A-2, B-3, C-1, LC
//10. Show all orders for U1.
//11. U1 : E-2, LC
    public static void main(String[] args) {

        FeedMe feedMe = new FeedMe();
        final LowestCostStrategy lowestCostStrategy = new LowestCostStrategy();

        Map<Item, Integer> itemsQuantityR1 = new HashMap<>();
        itemsQuantityR1.put(new Item("A", 50), Integer.MAX_VALUE);
        itemsQuantityR1.put(new Item("B", 100), Integer.MAX_VALUE);
        itemsQuantityR1.put(new Item("C", 200), Integer.MAX_VALUE);
        List<Item> menu = new ArrayList<>(itemsQuantityR1.keySet());
        feedMe.onBoard(new Restaurant(1, "R1", menu, itemsQuantityR1, 4.0f, 2));

        Map<Item, Integer> itemsQuantityR2 = new HashMap<>();
        itemsQuantityR2.put(new Item("A", 60), Integer.MAX_VALUE);
        itemsQuantityR2.put(new Item("B", 100), Integer.MAX_VALUE);
        List<Item> menu1 = new ArrayList<>(itemsQuantityR2.keySet());
        feedMe.onBoard(new Restaurant(2, "R2", menu1, itemsQuantityR2, 4.0f, 5));

        Map<Item, Integer> itemsQuantityR3 = new HashMap<>();
        itemsQuantityR3.put(new Item("C", 210), Integer.MAX_VALUE);
        List<Item> menu2 = new ArrayList<>(itemsQuantityR3.keySet());
        feedMe.onBoard(new Restaurant(3, "R3", menu2, itemsQuantityR3, 4.0f, 10));

        User user1 = new User("U1", feedMe);
        User user2 = new User("U2", feedMe);

        final HashMap<String, Integer> itemsU1 = new HashMap<>();
        itemsU1.put("A", 2);
        itemsU1.put("B", 3);
        itemsU1.put("C", 1);

        user1.placeOrder(lowestCostStrategy, itemsU1);

        final HashMap<String, Integer> itemsU2 = new HashMap<>();
        itemsU2.put("A", 1);
        itemsU2.put("B", 1);
        itemsU2.put("C", 2);

        user2.placeOrder(lowestCostStrategy, itemsU2);

        final HashMap<String, Integer> itemsU11 = new HashMap<>();
        itemsU11.put("C", 2);

        user1.placeOrder(lowestCostStrategy, itemsU11);

        final ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("C", 180));
        feedMe.updateMenuOfRestaurant("R3", Operation.UPDATE_EXISTING, items);

        final HashMap<String, Integer> user2Items = new HashMap<>();
        user2Items.put("C", 3);
        user2.placeOrder(lowestCostStrategy, user2Items);
    }
}
