package com.order.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tao on 2017/12/12.
 */
public class Comment {
    private int id;
    private Customer customer;
    private Food food;
    private String comment;
    private String response;
    private Date ctime;


    public String getCtime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyƒÍMM‘¬dd»’ HH:mm:ss");
        return  simpleDateFormat.format(ctime);
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
