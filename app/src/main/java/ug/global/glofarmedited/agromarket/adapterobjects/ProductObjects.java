package ug.global.glofarmedited.agromarket.adapterobjects;

public class ProductObjects {
    private String productname;
    private String productdescription;
    private String productprice;

    public ProductObjects() {
    }

    public ProductObjects(String productname, String productdescription, String productprice) {
        this.productname = productname;
        this.productdescription = productdescription;
        this.productprice = productprice;
    }

    public String getProductname() {
        return productname;
    }

    public String getProductdescription() {
        return productdescription;
    }


    public String getProductprice() {
        return productprice;
    }
}
