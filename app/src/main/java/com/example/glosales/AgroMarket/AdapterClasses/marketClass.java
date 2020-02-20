package com.example.glosales.AgroMarket.AdapterClasses;

public class marketClass {
    private String Farmname;
    private String Location;
    private String contact;
    private Integer photo;

    public marketClass(String farmname, String location, String contact, Integer photo) {
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
