package ug.global.glofarmedited.agromarket.adapterobjects;

public class OrderObject {
    private String productname;
    private String orderamount;
    private String shopname;
    private String quantity;
    private String shoplocation;
    private String timestamp;

    public OrderObject() {
    }

    public OrderObject(String productname, String orderamount, String shopname, String quantity, String shoplocation, String timestamp) {
        this.productname = productname;
        this.orderamount = orderamount;
        this.shopname = shopname;
        this.quantity = quantity;
        this.shoplocation = shoplocation;
        this.timestamp = timestamp;
    }

    public String getProductname() {
        return productname;
    }

    public String getOrderamount() {
        return orderamount;
    }

    public String getShopname() {
        return shopname;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getShoplocation() {
        return shoplocation;
    }
}
