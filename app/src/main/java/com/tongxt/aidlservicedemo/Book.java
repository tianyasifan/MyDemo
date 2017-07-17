package com.tongxt.aidlservicedemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tongxt on 2017/7/5.
 */

public class Book implements Parcelable{
    private String name;
    private String price;
    private String author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    protected Book(Parcel in) {
        name = in.readString();
        price = in.readString();
        author = in.readString();
    }

    public Book(String ... str){
        name = str[0];
        price = str[1];
        author = str[2];
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(author);
    }
}
