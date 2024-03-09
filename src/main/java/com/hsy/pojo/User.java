package com.hsy.pojo;

import java.util.Date;

public class User {

    //定义一个属性代表用户的编号
    private int id;
    //定义一个属性代表用户的姓名
    private String name;
    //定义一个属性代表用户的生日
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
    }

}
