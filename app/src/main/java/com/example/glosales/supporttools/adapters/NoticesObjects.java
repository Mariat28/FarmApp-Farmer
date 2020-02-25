package com.example.glosales.supporttools.adapters;

public class NoticesObjects {
    private String noticetitle;
    private String noticedesc;
    private String date;


    public NoticesObjects(String noticetitle, String noticedesc, String date) {
        this.noticetitle = noticetitle;
        this.noticedesc = noticedesc;
        this.date = date;
    }

    String getNoticetitle() {
        return noticetitle;
    }

    String getNoticedesc() {
        return noticedesc;
    }

    String getDate() {
        return date;
    }


}
