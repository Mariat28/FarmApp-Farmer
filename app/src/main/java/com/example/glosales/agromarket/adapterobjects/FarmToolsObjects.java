package com.example.glosales.agromarket.adapterobjects;

public class FarmToolsObjects {
    private String dealername;
    private String dealercontact;
    private String dealerdetails;
    private Integer dealercall, dealerphoto;

    public FarmToolsObjects(String dealername, String dealercontact, String dealerdetails, Integer dealercall, int dealerphoto) {
        this.dealername = dealername;
        this.dealercontact = dealercontact;
        this.dealerdetails = dealerdetails;
        this.dealercall = dealercall;
        this.dealerphoto = dealerphoto;
    }

    public String getDealername() {
        return dealername;
    }

    public String getDealercontact() {
        return dealercontact;
    }

    public String getDealerdetails() {
        return dealerdetails;
    }

    public Integer getDealerphoto() {
        return dealerphoto;
    }

    public Integer getDealercall() {
        return dealercall;
    }
}
