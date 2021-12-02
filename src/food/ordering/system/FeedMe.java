package food.ordering.system;

import food.ordering.system.exception.RestaurantNotFoundException;
import food.ordering.system.model.*;
import food.ordering.system.service.OrderService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FeedMe {
    private Set<Restaurant> restaurants = new HashSet<>();
    private OrderService orderService = new OrderService();

    public Set<Restaurant> onBoard(Restaurant restaurant){
        restaurants.add(restaurant);
        return restaurants;
    }

    public Restaurant updateMenuOfRestaurant(String name, Operation operation, List<Item> items) {
        final Restaurant restaurant = restaurants.stream().filter(r -> r.getName().equals(name)).findFirst().orElseThrow(RestaurantNotFoundException::new);
        restaurant.updateMenu(operation, items);
        return restaurant;
    }

    public Order placeOrder(SelectionStrategy strategy, Map<String, Integer> items) {
        final PlaceOrderRequest placeOrderRequest = new PlaceOrderRequest(strategy, items);
        return orderService.assignRestaurant(placeOrderRequest, restaurants);
    }
}
