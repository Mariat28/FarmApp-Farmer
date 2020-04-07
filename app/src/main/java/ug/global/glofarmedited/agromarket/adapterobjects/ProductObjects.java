package ug.global.glofarmedited.agromarket.adapterobjects;

public class ProductObjects {
    private String productname;
    private String productavailability;
    private String productprice;

    public ProductObjects() {
    }

    public ProductObjects(String productname, String productavailability, String productprice) {
        this.productname = productname;
        this.productavailability = productavailability;
        this.productprice = productprice;
    }

    public String getProductname() {
        return productname;
    }

    public String getProductavailability() {
        return productavailability;
    }


    public String getProductprice() {
        return productprice;
    }
}
