package food.ordering.system.service;

import food.ordering.system.model.Order;
import food.ordering.system.model.PlaceOrderRequest;
import food.ordering.system.model.Restaurant;
import food.ordering.system.model.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderService {
    private List<Order> orders = new ArrayList<>();

    public Order assignRestaurant(PlaceOrderRequest placeOrderRequest, Set<Restaurant> restaurants) {
        int[] num = {1,2};
        Arrays.stream(num).boxed().collect(Collectors.toList());
        final String restaurantName = placeOrderRequest.getStrategy().assignRestaurant(placeOrderRequest.getItems(), restaurants);
        int orderId = orders.size() +1;
        final Order order = new Order(restaurantName, orderId, Status.ACCEPTED);
        orders.add(order);
        return order;
    }
}
