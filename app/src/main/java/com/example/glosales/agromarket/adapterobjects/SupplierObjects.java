package com.example.glosales.agromarket.adapterobjects;

public class SupplierObjects {
    private String productname;
    private Integer photo;

    public SupplierObjects(String productname, Integer photo) {
        this.productname = productname;
        this.photo = photo;
    }

    public String getProductname() {
        return productname;
    }


    public Integer getPhoto() {
        return photo;
    }
}
