package com.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.dao.FoodDao;
import com.order.model.Food;
import com.order.service.FoodService;

import java.util.List;


@Service("foodService")
@Transactional
public class FoodServiceImpl implements FoodService{


	@Autowired
	private FoodDao foodDao;
	@Override
	public List<Food> getByKey(String key,int shop_id) {
		if(shop_id==-1){
			return foodDao.getByKey(key);
		}else {
			return foodDao.getByKey(key, shop_id);
		}
	}

	@Override
	public int add(Food food) {
		int status = foodDao.add(food);
		return status;
	}

	@Override
	public int delete(Food food) {
		int status = foodDao.deleteById(food.getId());
		return status;
	}

	@Override
	public int update(Food food) {
		int status = foodDao.update(food);
		return status;
	}

	@Override
	@Transactional(readOnly=true)
	public Food get(Food food) {
		Food f = foodDao.getById(food.getId());
		return f;
	}
	
}
