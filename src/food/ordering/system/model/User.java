package food.ordering.system.model;

import food.ordering.system.FeedMe;

import java.util.Map;

public class User {
    private String name;
    private FeedMe feedMe;

    public User(String name, FeedMe feedMe) {
        this.name = name;
        this.feedMe = feedMe;
    }

    public void placeOrder(LowestCostStrategy strategy, Map<String, Integer> items) {
        final Order order = feedMe.placeOrder(strategy, items);
        System.out.println("Order " + order.id + " is placed at Restaurant" + order.restaurantName);
    }
}
