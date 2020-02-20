package com.example.glosales.agromarket.adapterobjects;

public class MarketObject {
    private String Farmname;
    private String Location;
    private String contact;
    private Integer photo;

    public MarketObject(String farmname, String location, String contact, Integer photo) {
        Farmname = farmname;
        Location = location;
        this.contact = contact;
        this.photo = photo;
    }


    public String getFarmname() {
        return Farmname;
    }

    public String getLocation() {
        return Location;
    }

    public String getContact() {
        return contact;
    }

    public Integer getPhoto() {
        return photo;
    }
}
