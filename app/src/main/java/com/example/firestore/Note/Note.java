package com.example.firestore.Note;

public class Note {



    private String id;
    private String username;
    private String Gmail;
    private String password;
    private int priority;

    public Note(String username, String Gmail, String password,int priority) {
        this.username = username;
        this.Gmail = Gmail;
        this.password = password;
        this.priority=priority;
    }

    public Note() {
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public String getPassWord() {
        return password;
    }
    public String getDocumentId() {
        return id;
    }

    public void setDocumentId(String id) {
        this.id = id;
    }

    public void setPassWord(String password) {
        this.password = password;
    }
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
