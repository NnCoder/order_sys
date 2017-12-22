package com.order.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.order.model.Food;
import com.order.model.Shop;

public class FoodRowMapper implements RowMapper<Food> {

	@Override
	public Food mapRow(ResultSet rs, int row) throws SQLException {
		int id = rs.getInt("id");
		int shop_id = rs.getInt("shop_id");
		String description = rs.getString("description");
		String name = rs.getString("name");
		String imgSrc = rs.getString("imgSrc");
		String price = rs.getString("price");

		Food food = new Food(id, name, imgSrc, price, description);
		Shop shop = new Shop();
		shop.setId(shop_id);
		food.setShop(shop);
		return food;
	}

}
