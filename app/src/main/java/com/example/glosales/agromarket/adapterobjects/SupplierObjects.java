package com.example.glosales.agromarket.adapterobjects;

public class SupplierObjects {
    private String Suppliername, suppliercontact, supplierlocation;
    private Integer photo;

    public SupplierObjects(String suppliername, String suppliercontact, String supplierlocation, Integer photo) {
        Suppliername = suppliername;
        this.suppliercontact = suppliercontact;
        this.supplierlocation = supplierlocation;
        this.photo = photo;
    }

    public String getSuppliername() {
        return Suppliername;
    }

    public String getSuppliercontact() {
        return suppliercontact;
    }

    public String getSupplierlocation() {
        return supplierlocation;
    }


    public Integer getPhoto() {
        return photo;
    }
}
