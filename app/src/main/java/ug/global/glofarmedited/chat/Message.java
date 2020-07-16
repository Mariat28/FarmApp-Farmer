package ug.global.glofarmedited.chat;

public class Message {
    String time, message, sender, recipient;

    public Message(String time, String message, String sender, String recipient) {
        this.time = time;
        this.message = message;
        this.sender = sender;
        this.recipient = recipient;
    }

    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }
}

