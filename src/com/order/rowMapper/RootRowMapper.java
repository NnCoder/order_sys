package com.order.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.order.model.Root;

public class RootRowMapper implements RowMapper<Root> {

	@Override
	public Root mapRow(ResultSet rs, int row) throws SQLException {
		int id = rs.getInt("id");
		String username = rs.getString("username");
		String password = rs.getString("password");
		Root root = new Root(id, username, password);
		return root;
	}

}
