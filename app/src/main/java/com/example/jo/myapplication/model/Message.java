package com.example.jo.myapplication.model;

/**
 * Created by Jo on 22/03/2015.
 */
public class Message {

    String content;
    String author;

    public Message(String author, String content) {
        this.author = author;
        this.content = content;
    }

    @Override
    public String toString() {
        return author+" : " +content;
    }
}
