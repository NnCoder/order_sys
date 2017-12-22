package com.order.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.order.model.Customer;

public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int row) throws SQLException {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String password = rs.getString("password");
		String phoneNum = rs.getString("phoneNum");
		String address = rs.getString("address");
		Customer customer = new Customer(id,address, phoneNum, password, name);
		return customer;
	}

}
