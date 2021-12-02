package food.ordering.system.model;

import java.util.Map;
import java.util.Set;

public interface SelectionStrategy {
    String assignRestaurant(Map<String, Integer> items, Set<Restaurant> restaurants);
}
