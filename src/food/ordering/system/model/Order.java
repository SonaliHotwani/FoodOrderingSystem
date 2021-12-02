package food.ordering.system.model;

public class Order {
    String restaurantName;
    int id;
    Status status;

    public Order(String restaurantName, int orderId, Status status) {
        this.restaurantName = restaurantName;
        this.id = orderId;
    }

}
