package com.doctorn.voiceChat;

public class FirebaseUser {

    String id,name,type,key;

    public FirebaseUser() {
    }

    public FirebaseUser(String id, String name, String type,String key) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.key=key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
