package com.order.service;

import com.order.model.Food;

import java.util.List;

public interface FoodService {
	public Food get(Food food);
	public int add(Food food);
	public int delete(Food food);
	public int update(Food food);
	public List<Food> getByKey(String key,int shop_id);
}
