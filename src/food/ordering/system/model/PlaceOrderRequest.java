package food.ordering.system.model;

import java.util.Map;

public class PlaceOrderRequest {
    SelectionStrategy strategy;
    private final Map<String, Integer> items;

    public SelectionStrategy getStrategy() {
        return strategy;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public PlaceOrderRequest(SelectionStrategy strategy, Map<String, Integer> items) {
        this.strategy = strategy;
        this.items = items;
    }
}
