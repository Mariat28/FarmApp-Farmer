package ug.global.glofarmedited.agromarket.adapterobjects;

public class ProductObjects {
    private String productname;
    private int productavailability;
    private String productprice;
    private String productunit;


    public ProductObjects() {
    }

    public ProductObjects(String productname, int productavailability, String productprice, String productunit) {
        this.productname = productname;
        this.productavailability = productavailability;
        this.productprice = productprice;

        this.productunit = productunit;
    }

    public String getProductname() {
        return productname;
    }

    public int getProductavailability() {
        return productavailability;
    }


    public String getProductprice() {
        return productprice;
    }

    public String getProductunit() {
        return productunit;
    }
}
