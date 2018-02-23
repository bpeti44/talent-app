package com.bartonpeter.talentapp;

/**
 * Created by petib on 2018. 02. 21..
 */

public class Content {

    private String post;
    private String author;

    public Content(){}

    public Content(String post_, String author_){
        post = post_;
        author = author_;
    }

    public String getPost() {
        return post;
    }

    public String getAuthor() {
        return author;
    }
}
