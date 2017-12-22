package com.order.dao.impl;

import com.order.dao.CommentDao;
import com.order.model.Comment;
import com.order.model.Customer;
import com.order.model.Food;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Tao on 2017/12/12.
 */
@Repository("CommentDao")
public class CommentDaoImpl extends BaseDaoImpl implements CommentDao{
    @Override
    public int add(Comment comment) {
        String sql = "insert into comment(food_id,comment,customer_id,time) values(?,?,?,now())";
        int status = getJdbcTemplate().update(sql,comment.getFood().getId(),comment.getComment(),comment.getCustomer().getId());
        return status;
    }

    @Override
    public List<Comment> getByCustomer(int customer_id) {
        String sql = "select comment.id as comment_id," +
                "food.name as food_name," +
                "food.imgSrc as food_imgSrc," +
                "comment.comment as comment_comment," +
                "comment.response as comment_response," +
                "comment.time as comment_time " +
                "from comment left join food on comment.food_id=food.id where customer_id=?";
        List<Map<String,Object>> rs = getJdbcTemplate().queryForList(sql,customer_id);
        List<Comment> comments = new ArrayList<>();/* 钻石写法 */
        Food food = null;
        Comment comment = null;
        //取出数据
        for(Map<String,Object> result:rs){
            int comment_id = (int) result.get("comment_id");
            String comment_comment = (String) result.get("comment_comment");
            String comment_response = (String) result.get("comment_response");
            Timestamp time = (Timestamp)result.get("comment_time");
            Date comment_time = new Date(time.getTime());
            String food_name = (String)result.get("food_name");
            String imgSrc = (String)result.get("food_imgSrc");
            comment = new Comment();
            comment.setId(comment_id);
            comment.setComment(comment_comment);
            comment.setResponse(comment_response);
            comment.setCtime(comment_time);
            food = new Food();
            food.setName(food_name);
            food.setImgSrc(imgSrc);
            comment.setFood(food);
            comments.add(comment);
        }
        return comments;
    }

    @Override
    public List<Comment> getByShop(int shop_id) {
        String sql = "select comment.id as comment_id," +
                "food.imgSrc as food_imgSrc," +
                "food.name as food_name," +
                "customer.name as customer_name," +
                "comment.comment as comment_comment," +
                "comment.response as comment_response," +
                "comment.time as comment_time " +
                "from comment left join customer on comment.customer_id=customer.id " +
                "left join food on comment.food_id=food.id " +
                "where food.id in (select id from food where shop_id=?)";
        List<Map<String,Object>> rs = getJdbcTemplate().queryForList(sql,shop_id);
        List<Comment> comments = new ArrayList<>();/* 钻石写法 */
        Food food = null;
        Comment comment = null;
        Customer customer = null;
        //取出数据
        for(Map<String,Object> result:rs){
            int comment_id = (int) result.get("comment_id");
            String comment_comment = (String) result.get("comment_comment");
            String comment_response = (String)result.get("comment_response");
            Timestamp time = (Timestamp)result.get("comment_time");
            Date comment_time = new Date(time.getTime());
            String food_name = (String)result.get("food_name");
            String imgSrc = (String)result.get("food_imgSrc");
            String customer_name = (String)result.get("customer_name");
            //初始化评论类
            comment = new Comment();
            comment.setId(comment_id);
            comment.setComment(comment_comment);
            comment.setResponse(comment_response);
            comment.setCtime(comment_time);
            //初始化食物类
            food = new Food();
            food.setName(food_name);
            food.setImgSrc(imgSrc);
            //初始化用户类
            customer = new Customer();
            customer.setName(customer_name);
            comment.setFood(food);
            comment.setCustomer(customer);
            comments.add(comment);
        }
        return comments;
    }

    @Override
    public int updateResponse(Comment comment) {
        String sql = "update comment set response=? where id=?";
        return getJdbcTemplate().update(sql,"商家回复: "+comment.getResponse(),comment.getId());
    }
}

