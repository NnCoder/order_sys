package com.order.dao;

import com.order.model.Food;

import java.util.List;

public interface FoodDao {
	public Food getById(int food_id);
	public int deleteById(int food_id);
	public int add(Food food);
	public int update(Food food);
	public List<Food> getByKey(String key);
	public List<Food> getByKey(String key,int shop_id);
}
