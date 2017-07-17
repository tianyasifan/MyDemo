package com.txt.designpattern.factory.prototype;

/**
 * Created by tongxt on 2017/6/27.
 */

public class Student implements Cloneable{
    private int number;
    private String name;
    private Address mAddress;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(Address add){
        mAddress = add;
    }

    public void show() {
        System.out.println("number:" + number + ", name:" + name );
    }

    public Object clone(){
        Student student = null;
        try {
            student = (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return student;
    }

    class Address implements Cloneable{
        private String add;
        public void setAdd(String add){
            this.add = add;
        }
    }
}
