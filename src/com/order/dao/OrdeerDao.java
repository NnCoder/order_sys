package com.order.dao;

import com.order.model.Ordeer;

public interface OrdeerDao {
	public Ordeer getById(int order_id);
	public int deleteById(int order_id);
	public int updateStatus(int order_id,int status);
	public int updateArriveTime(int order_id);
	public int add(Ordeer ordeer);
}
