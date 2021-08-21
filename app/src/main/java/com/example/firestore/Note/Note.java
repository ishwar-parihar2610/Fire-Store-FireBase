package com.example.firestore.Note;

public class Note {

    private String id;
    private String name;
    private String Gmail;
    private String password;
    private int priority;

    public Note(String name, String Gmail, String password,int priority) {
        this.name = name;
        this.Gmail = Gmail;
        this.password = password;
        this.priority=priority;
    }

    public Note() {
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

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }




}
