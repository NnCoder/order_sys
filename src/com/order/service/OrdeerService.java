package com.order.service;

import com.order.model.Ordeer;

public interface OrdeerService {
	public int add(Ordeer ordeer);
	public int delete(Ordeer ordeer);
	public int updateStatus(Ordeer ordeer,int status);
}
