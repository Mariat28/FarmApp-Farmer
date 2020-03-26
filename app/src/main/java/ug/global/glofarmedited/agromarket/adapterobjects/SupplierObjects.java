package ug.global.glofarmedited.agromarket.adapterobjects;

public class SupplierObjects {
    private String productname;
    private String price;
    private String availablesupply;
    private Integer photo;

    public SupplierObjects(String productname, String price, String availablesupply, Integer photo) {
        this.productname = productname;
        this.price = price;
        this.availablesupply = availablesupply;
        this.photo = photo;
    }

    public String getProductname() {
        return productname;
    }


    public Integer getPhoto() {
        return photo;
    }

    public String getPrice() {
        return price;
    }

    public String getAvailablesupply() {
        return availablesupply;
    }

}
