package ug.global.glofarmedited.supporttools.adapters;

public class ListViewObjects {

    private String manualdate;
    private String manualname;

    public ListViewObjects(String manualname, String manualdate) {
        this.manualname = manualname;
        this.manualdate = manualdate;
    }

    String getManualname() {
        return manualname;
    }

    String getManualdate() {
        return manualdate;
    }


}
