package ug.global.glofarmedited.agromarket;

public class SalesObjects {
    private String productname;
    private Long productcost;
    private String timestamp;
    private String quantity;
    private String farmname;

    public SalesObjects() {
    }

    public SalesObjects(String productname, Long productcost, String timestamp, String quantity, String farmname) {
        this.productname = productname;
        this.productcost = productcost;
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.farmname = farmname;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public long getProductcost() {
        return productcost;
    }

    public void setProductcost(Long productcost) {
        this.productcost = productcost;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getFarmname() {
        return farmname;
    }

    public void setFarmname(String farmname) {
        this.farmname = farmname;
    }
}
