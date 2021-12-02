package food.ordering.system.model;

import java.util.*;
import java.util.stream.Collectors;

public class LowestCostStrategy implements SelectionStrategy {
    @Override
    public String assignRestaurant(Map<String, Integer> items, Set<Restaurant> restaurants) {
        Restaurant selectedRestaurant = null;
        List<Restaurant> eligibleRestaurant = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            final Map<Item, Integer> restaurantItemsQuantity = restaurant.getItemsQuantity();
            if(allItemsPresentInRestaurant(items, restaurantItemsQuantity)
                    &&  quantityMatches(restaurantItemsQuantity, items)) {
                eligibleRestaurant.add(restaurant);
            }
        }
        selectedRestaurant = getLowestCostRestuarant(eligibleRestaurant, items);
        return selectedRestaurant.getName();
    }

    private Restaurant getLowestCostRestuarant(List<Restaurant> eligibleRestaurants, Map<String, Integer> items) {
        final Optional<Restaurant> lowestCostRestaurant = eligibleRestaurants.stream().max((a, b) -> {
            int costA = 0;
            int costB = 0;
            final List<Map.Entry<Item, Integer>> allPresetItemsInA = filterOrderedItems(items, a);
            final List<Map.Entry<Item, Integer>> allPresetItemsInB = filterOrderedItems(items, b);
            for (Map.Entry<Item, Integer> integerEntry : allPresetItemsInA) {
                costA += integerEntry.getKey().getPrice();
            }
            for (Map.Entry<Item, Integer> itemIntegerEntry : allPresetItemsInB) {
                costB += itemIntegerEntry.getKey().getPrice();
            }
            return costB - costA;
        });
        return lowestCostRestaurant.orElseThrow(RuntimeException::new);
    }

    private boolean allItemsPresentInRestaurant(Map<String, Integer> items, Map<Item, Integer> restaurantItemsQuantity) {
        return restaurantItemsQuantity.keySet().stream().map(Item::getName).collect(Collectors.toList()).containsAll(items.keySet());
    }

    private boolean quantityMatches(Map<Item, Integer> restaurantItemsQuantity, Map<String, Integer> items) {
        final List<Map.Entry<Item, Integer>> allPresetItems = restaurantItemsQuantity.entrySet().stream()
                .filter(restaurantItem -> items.containsKey(restaurantItem.getKey().getName()))
                .collect(Collectors.toList());

        for (Map.Entry<Item, Integer> item : allPresetItems) {
            if (items.get(item.getKey().getName()) > item.getValue()) return false;
        }
        return true;
    }

    private List<Map.Entry<Item, Integer>> filterOrderedItems(Map<String, Integer> items, Restaurant a) {
        return a.getItemsQuantity().entrySet().stream()
                .filter(restaurantItem -> items.containsKey(restaurantItem.getKey().getName()))
                .collect(Collectors.toList());
    }
}
