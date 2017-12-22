package com.order.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.order.model.Shop;

public class ShopRowMapper implements RowMapper<Shop> {

	@Override
	public Shop mapRow(ResultSet rs, int row) throws SQLException {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String description = rs.getString("description");
		String password = rs.getString("password");
		String imgSrc = rs.getString("imgSrc");
		String phoneNum = rs.getString("phoneNum");
		String address = rs.getString("address");
		String idcard = rs.getString("idcard");
		
		Shop shop = new Shop(id,idcard,address, phoneNum, password, name, description, imgSrc);
		return shop;
	}

}
