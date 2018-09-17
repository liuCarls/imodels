package com.carl.ibatis;

/**
 * Created by user on 2018/8/10.
 */
public class Paramer {
    private int tableSuf;
    private int id;
    private String name;

    public int getTableSuf() {
        int value = 201801;
        System.out.println(this.id);
        int tmp = this.id%12;
        tableSuf = value + tmp;
        return tableSuf;
    }


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
}
