package com.example.chat_toolbar.Model;

public class Scholar {

    String image, title, link;

    Scholar() {

    }

    public Scholar(String image, String title, String link) {
        this.image = image;
        this.title = title;

    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
