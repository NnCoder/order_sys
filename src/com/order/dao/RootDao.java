package com.order.dao;

import com.order.model.Root;

public interface RootDao {
	public String getPswById(int id);
	public int updatePsw(String newPsw,int id);
	public Root getByUN(String username);
	public Root getByid(int id);
	public String getPswByUsername(String username);
}
