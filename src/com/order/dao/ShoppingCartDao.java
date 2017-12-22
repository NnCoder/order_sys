package com.order.dao;

import java.util.List;

import com.order.model.Shop;
import com.order.model.ShoppingCart;

public interface ShoppingCartDao {
	public int deleteById(int cart_id);
	public int add(ShoppingCart shoppingCart);
	public int addToOrder(List<Integer> shoppingCartIds);
	public int deleteByIds(List<Integer> shoppingCartIds);
	public List<ShoppingCart> getByCustomerId(int customer_id);
}
