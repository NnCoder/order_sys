package com.order.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.order.dao.OrdeerDao;
import com.order.model.Ordeer;

@Repository("ordeerDao")
public class OrdeerDaoImpl extends BaseDaoImpl implements OrdeerDao{

	@Override
	public Ordeer getById(int order_id) {
		return null;
	}

	@Override
	public int deleteById(int order_id) {
		String sql = "delete from ordeer where id=?";
		int status = getJdbcTemplate().update(sql, order_id);
		return status;
	}

	@Override
	public int updateStatus(int order_id, int status) {
		String sql = "update ordeer set status=? where id=?";
		//返回状态码
		int code = getJdbcTemplate().update(sql,status,order_id);
		return code;
	}

	@Override
	public int updateArriveTime(int order_id) {
		System.out.println(order_id);
		String sql = "update ordeer set arriveTime=now() where id=?";

		return getJdbcTemplate().update(sql,order_id);
	}

	@Override
	public int add(final Ordeer ordeer) {
		String sql = "insert into "
				+ "ordeer(orderTime,arriveTime,status,customer_id,shop_id,food_id,type,remarks) "
				+ "values(?,?,?,?,?,?,?,?)";
		return getJdbcTemplate().update(
				sql,
				new Date(),
				ordeer.getArriveTime(),
				0,
				ordeer.getCustomer().getId(),
				ordeer.getShop().getId(),
				ordeer.getFood().getId(),
				ordeer.getType(),
				ordeer.getRemarks()
				);
	}
	
}
