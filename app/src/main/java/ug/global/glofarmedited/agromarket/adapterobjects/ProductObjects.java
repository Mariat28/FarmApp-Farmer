package ug.global.glofarmedited.agromarket.adapterobjects;

public class ProductObjects {
    private String productname;
    private String productdescription;
    private String price;

    public ProductObjects() {
    }

    public ProductObjects(String productname, String productdescription, String price) {
        this.productname = productname;
        this.productdescription = productdescription;
        this.price = price;
    }

    public String getProductname() {
        return productname;
    }

    public String getProductdescription() {
        return productdescription;
    }


    public String getPrice() {
        return price;
    }
}
