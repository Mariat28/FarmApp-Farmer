package ug.global.glofarmedited.chat;

public class ChatPerson {
    String photo, name, farmname, key;

    public ChatPerson(String photo, String name, String farmname, String key) {
        this.photo = photo;
        this.key = key;
        this.name = name;
        this.farmname = farmname;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getFarmname() {
        return farmname;
    }
}
