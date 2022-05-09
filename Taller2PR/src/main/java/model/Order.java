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

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
