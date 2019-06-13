package com.example.gitcommithistory.models;

// Simple Java class for storing
// Git commit Author name, Message and Commit number
public class DataClass {
    String author;
    String message;
    String commitid;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCommitid() {
        return commitid;
    }

    public void setCommitid(String commitid) {
        this.commitid = commitid;
    }
}
