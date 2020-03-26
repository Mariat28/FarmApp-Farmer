package ug.global.glofarmedited.agromarket.adapterobjects;

public class FarmToolsAdapterObjects {

    private String name;
    private String available;
    private String price;

    public FarmToolsAdapterObjects(String name, String available, String price) {
        this.name = name;
        this.available = available;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getAvailable() {
        return available;
    }

    public String getPrice() {
        return price;
    }

}
