package com.example.glosales.agromarket.adapterobjects;

public class FarmToolsObjects {
    private String name;
    private String available;
    private String price;

    public FarmToolsObjects(String price, String name, String available) {
        this.price = price;
        this.name = name;
        this.available = available;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getAvailable() {
        return available;
    }


}
