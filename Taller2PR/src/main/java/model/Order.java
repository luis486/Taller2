package model;

public class Order {

    private int orderId;
    private String productName;
    private int amount;

    public Order(int orderId, String productName, int amount) {
        this.orderId = orderId;
        this.productName = productName;
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getProductName() {
        return productName;
    }

    public int getAmount() {
        return amount;
    }

}
