package com.order.service.impl;

import com.order.dao.CommentDao;
import com.order.model.Comment;
import com.order.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Tao on 2017/12/12.
 */
@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentDao commentDao;
    @Override
    public int add(Comment comment) {
        return commentDao.add(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getByCustomer(int customer_id) {
        return commentDao.getByCustomer(customer_id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getByShop(int shop_id) {
        return commentDao.getByShop(shop_id);
    }

    @Override
    public int response(Comment comment) {
        return commentDao.updateResponse(comment);
    }
}
