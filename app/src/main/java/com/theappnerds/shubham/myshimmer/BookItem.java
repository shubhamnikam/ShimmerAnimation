package com.theappnerds.shubham.myshimmer;

public class BookItem {

    int id;
    String name;
    String description;
    double price;
    String thumbnail;
    String author;
    String release;

    public BookItem(int id, String name, String description, double price, String thumbnail, String author, String release) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.thumbnail = thumbnail;
        this.author = author;
        this.release = release;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getAuthor() {
        return author;
    }

    public String getRelease() {
        return release;
    }


}
