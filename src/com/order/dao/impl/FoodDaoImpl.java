package com.order.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.order.dao.FoodDao;
import com.order.model.Food;
import com.order.rowMapper.FoodRowMapper;

@Repository("foodDao")
public class FoodDaoImpl extends BaseDaoImpl implements FoodDao{
	@Override
	public List<Food> getByKey(String key, int shop_id) {
		String sql = "select * from food where name like ? and shop_id=?";
		List<Map<String,Object>> rs = getJdbcTemplate().queryForList(sql,"%"+key+"%",shop_id);
		List<Food> foods = new ArrayList<Food>();
		for(Map<String,Object> result:rs){
			int id = (int) result.get("id");
			String description = (String) result.get("description");
			String name = (String) result.get("name");
			String imgSrc = (String) result.get("imgSrc");
			String price = (String) result.get("price");
			Food food = new Food(id,name,imgSrc,price,description);
			food.setShop_id(shop_id);
			foods.add(food);
		}
		return foods;
	}

	@Override
	public List<Food> getByKey(String key) {
		String sql = "select * from food where name like ?";
		List<Map<String,Object>> rs = getJdbcTemplate().queryForList(sql,"%"+key+"%");
		List<Food> foods = new ArrayList<Food>();
		for(Map<String,Object> result:rs){
			int id = (int) result.get("id");
			String description = (String) result.get("description");
			String name = (String) result.get("name");
			String imgSrc = (String) result.get("imgSrc");
			String price = result.get("price").toString();
			int shop_id = (int)result.get("shop_id");
			Food food = new Food(id,name,imgSrc,price,description);
			food.setShop_id(shop_id);
			foods.add(food);
		}
		return foods;
	}

	@Override
	public Food getById(int food_id) {
		String sql = "select * from food where id=?";
		Food food = 
				getJdbcTemplate().queryForObject(sql,new FoodRowMapper(),food_id);
		return food;
	}

	@Override
	public int deleteById(int food_id) {
		String sql = "delete from food where id=?";
		int status = getJdbcTemplate().update(sql,food_id);
		return status;
	}

	@Override
	public int add(final Food food) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int status = getJdbcTemplate().update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				String sql = "insert into "
						+ "food(name,description,price,imgSrc,shop_id) "
						+ "values(?,?,?,?,?)";
				PreparedStatement pStatement = 
						conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				pStatement.setString(1, food.getName());
				pStatement.setString(2, food.getDescription());
				pStatement.setString(3, food.getPrice());
				pStatement.setString(4, food.getImgSrc());
				pStatement.setInt(5, food.getShop().getId());
				return pStatement;
			}
		},keyHolder);
		int id = keyHolder.getKey().intValue();
		food.setId(id);
		return status;
	}

	@Override
	public int update(Food food) {
		String sql = "update food set name=?,description=?,price=? where id=?";
		int status = getJdbcTemplate().update(
						sql,
						food.getName(),
						food.getDescription(),
						food.getPrice(),
						food.getId()
						);
		return status;
	}
	
}
