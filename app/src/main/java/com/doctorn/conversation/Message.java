package com.doctorn.conversation;

public class Message {

    private String message,sender,receiver,date,tokenSend,tokenReceive;

    public Message(String message, String sender, String receiver, String date, String tokenSend, String tokenReceive) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
        this.tokenSend = tokenSend;
        this.tokenReceive = tokenReceive;
    }

    public String getTokenSend() {
        return tokenSend;
    }

    public void setTokenSend(String tokenSend) {
        this.tokenSend = tokenSend;
    }

    public String getTokenReceive() {
        return tokenReceive;
    }

    public void setTokenReceive(String tokenReceive) {
        this.tokenReceive = tokenReceive;
    }

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getDate() {
        return date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setDate(String date) {
        this.date = date;
    }

}