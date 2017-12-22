package com.order.controller;

import com.opensymphony.xwork2.ActionSupport;
import com.order.model.Comment;
import com.order.model.Customer;
import com.order.service.CommentService;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * Created by Tao on 2017/12/12.
 */
@Controller
@Scope("prototype")
public class CommentAction extends ActionSupport implements SessionAware{
    private Map<String,Object> session;
    private Comment comment;
    private List<Comment> comments;
    @Autowired
    private CommentService commentService;
    public String add(){
        Customer customer = new Customer();
        int customer_id = (int) session.get("customer_id");
        customer.setId(customer_id);
        comment.setCustomer(customer);
        commentService.add(comment);
        return "json";
    }

    public String response(){
        commentService.response(comment);
        return  "json";
    }

    public String queryForCustomer(){
        int customer_id = (int)session.get("customer_id");
        comments = commentService.getByCustomer(customer_id);
        return SUCCESS;
    }

    public String queryForShop(){
        int shop_id = (int)session.get("shop_id");
        comments = commentService.getByShop(shop_id);
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
