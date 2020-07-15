package ug.global.glofarmedited.agromarket.adapterobjects;

public class SupplierObjects {
    private String supplyname;
    private String price;
    private String availableamount;
    private Integer photo;

    public SupplierObjects() {
    }

    public SupplierObjects(String supplyname, String price, String availableamount) {
        this.supplyname = supplyname;
        this.price = price;
        this.availableamount = availableamount;
        //   this.photo = photo;
    }

    public String getSupplyname() {
        return supplyname;
    }


    public Integer getPhoto() {
        return photo;
    }

    public String getPrice() {
        return price;
    }

    public String getAvailableamount() {
        return availableamount;
    }

}
