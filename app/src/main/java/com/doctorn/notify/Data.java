package com.doctorn.notify;

public class Data {
    private String user;
    private String body;
    private String title;
    private int icon;
    private String seneted;

    public Data() {
    }

    public Data(String user, String body, String title, int icon, String seneted) {
        this.user = user;
        this.body = body;
        this.title = title;
        this.icon = icon;
        this.seneted = seneted;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getSeneted() {
        return seneted;
    }

    public void setSeneted(String seneted) {
        this.seneted = seneted;
    }
}
