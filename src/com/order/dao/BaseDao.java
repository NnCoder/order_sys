package com.order.dao;

import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;

public interface BaseDao {
	
	public JdbcTemplate getJdbcTemplate();
	public Session getSession();
}
