package ug.global.glofarmedited.farmfinancials.adapterobjects;


public class SalesObjects {
    private String name;
    private String amount;

    public SalesObjects(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }
}

