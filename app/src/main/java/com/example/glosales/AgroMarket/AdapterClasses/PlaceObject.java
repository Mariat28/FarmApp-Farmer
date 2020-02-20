package com.example.glosales.AgroMarket.AdapterClasses;

public class PlaceObject {
    private String name;
    private String description;
    //, String key, String photo---cut from the constructor
    private String key;
    private String photo;

    public PlaceObject(String name, String description) {
        this.name = name;
        this.description = description;
        this.key = key;
        this.photo = photo;
    }

    public String getName() {


        return name;
    }

    public String getDescription() {

        return description;
    }

    public String getKey() {


        return key;
    }

    public String getPhoto() {


        return photo;
    }

}
