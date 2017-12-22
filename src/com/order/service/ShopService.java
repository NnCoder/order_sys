package com.order.service;

import java.util.List;
import java.util.Set;

import com.order.model.Food;
import com.order.model.Ordeer;
import com.order.model.Shop;

public interface ShopService {
	public int register(Shop shop);
	public Shop getByPhoneNum(Shop shop);
	public Shop get(Shop shop);
	public List<Shop> getAll(int status);
	public List<Shop> getOpenShops();
	public int updateOpen(int shop_id,int open);
	public List<Shop> getByKey(String message);
	public int updateStatus(Shop shop,int status);
	public int update(Shop shop);
	public int modifyPsw(Shop shop);
	public Set<Food> getFoods(Shop shop);
	public Set<Ordeer> getOrdeers(Shop shop);
	public Set<Ordeer> getOrdeers(Shop shop,int status,int type);
	public Set<Ordeer> getTurnover(Shop shop,int recent_day);
}
