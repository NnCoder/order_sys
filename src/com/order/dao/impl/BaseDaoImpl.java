package com.order.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.order.dao.BaseDao;

public class BaseDaoImpl implements BaseDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private SessionFactory sessionFactory;
	
	public JdbcTemplate getJdbcTemplate(){
		return jdbcTemplate;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
}
