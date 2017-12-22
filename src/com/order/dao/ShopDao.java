package com.order.dao;

import java.util.List;
import java.util.Set;

import com.order.model.Food;
import com.order.model.Ordeer;
import com.order.model.Shop;

public interface ShopDao {
	public int add(Shop shop);
	public Shop getByPhoneNum(String phoneNum);
	public Shop getById(int shop_id);
	public List<Shop> getAll(int status);
	public List<Shop> getOpenshops();
	public int updateOpen(int shop_id,int open);
	public List<Shop> getByKey(String message);
	public int update(Shop shop);
	public int updatePswById(int id,String newPsw);
	public int updateStatusById(int id,int status);
	public Set<Food> getFoodsById(int shop_id);
	public Set<Ordeer> getOrdeersById(int shop_id);
	public Set<Food> getFoods(int order_id);
	public Set<Ordeer> getOrdeers(int shop_id,int status,int type);

	/**
	 * ?????????????????????????
	 * @param shop_id
	 * @param recent_day
	 * @return
	 */
	public Set<Ordeer> getTurnover(int shop_id,int recent_day);
	public int getStatusByPhoneNum(String shop_phoneNum);

		
}
