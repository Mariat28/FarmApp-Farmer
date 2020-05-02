package ug.global.glofarmedited.farmfinancials.adapterobjects;


public class SalesObjects {
    private String productname;
    private Long productcost;
    private String timestamp;

    public SalesObjects() {
    }

    public SalesObjects(String name, Long productcost, String timestamp) {
        this.productname = name;
        this.productcost = productcost;
        this.timestamp = timestamp;
    }

    public String getProductname() {
        return productname;
    }

    public Long getProductcost() {
        return productcost;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

