package ug.global.glofarmedited.agromarket.adapterobjects;

public class OrderObject {
    private String clientname;
    private String clientcontact;
    private String orderdetails;

    public OrderObject(String clientname, String clientcontact, String orderdetails) {
        this.clientname = clientname;
        this.clientcontact = clientcontact;
        this.orderdetails = orderdetails;
    }

    public String getClientname() {
        return clientname;
    }

    public String getClientcontact() {
        return clientcontact;
    }

    public String getOrderdetails() {
        return orderdetails;
    }
}
