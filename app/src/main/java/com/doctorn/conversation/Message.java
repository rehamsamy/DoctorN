package com.doctorn.conversation;

public class Message {
    private String message,name,time,token;
    int id;

//    public Message(String message, String name, String time) {
//        this.message = message;
//        this.name = name;
//        this.time = time;
//    }

    public Message(String token, int id, String message) {
        this.message = message;
       this.id=id;
       this.token=token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public int getId() {
        return id;
    }

    public Message() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }


    public String getTime() {
        return time;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void setTime(String time) {
        this.time = time;
    }
}
