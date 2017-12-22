package com.order.dao.impl;

import org.springframework.stereotype.Repository;

import com.order.dao.RootDao;
import com.order.model.Root;
import com.order.rowMapper.RootRowMapper;

@Repository("rootDao")
public class RootDaoImpl extends BaseDaoImpl implements RootDao{

	@Override
	public String getPswById(int id) {
		String sql = "select password from root where id=?";
		return getJdbcTemplate().queryForObject(sql,new Object[]{id},String.class);
	}

	@Override
	public int updatePsw(String newPsw, int id) {
		String sql = "update root set password=? where id=?";
		return getJdbcTemplate().update(sql,newPsw,id);
	}
	
	@Override
	public Root getByid(int id) {
		//暂不需要
		String sql = "select * from root where id=?";
		return getJdbcTemplate().queryForObject(sql,new RootRowMapper(),id);
	}

	@Override
	public String getPswByUsername(String username) {
		String sql = "select password from root where username=?";
		return getJdbcTemplate().queryForObject(sql, String.class, username);
	}

	@Override
	public Root getByUN(String username) {
		String sql = "select * from root where username=?";
		return getJdbcTemplate().queryForObject(sql,new RootRowMapper(),username);
	}
	
}
