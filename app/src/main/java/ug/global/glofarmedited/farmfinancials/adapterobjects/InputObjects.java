package ug.global.glofarmedited.farmfinancials.adapterobjects;

public class InputObjects {
    private String inputname;
    private String inputcost;

    public InputObjects() {
    }

    public InputObjects(String inputname, String inputcost) {
        this.inputname = inputname;
        this.inputcost = inputcost;
    }

    public String getInputname() {
        return inputname;
    }

    public String getInputcost() {
        return inputcost;
    }
}
