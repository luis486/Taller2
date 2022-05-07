package model;

public class   OrderInformation {

    private String info;
    private int amountProducts;
    private int price;

    public OrderInformation(String info, int amountProducts, int price) {
        this.info = info;
        this.amountProducts = amountProducts;
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getAmountProducts() {
        return amountProducts;
    }

    public void setAmountProducts(int amountProducts) {
        this.amountProducts = amountProducts;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
