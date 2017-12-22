package com.order.service;

import java.util.List;

import com.order.model.Shop;
import com.order.model.ShoppingCart;

public interface ShoppingCartService {
	
	public int delete(List<Integer> shoppingCartIds);
	public int delete(ShoppingCart shoppingCart);
	public int add(ShoppingCart shoppingCart);
	public int addToOrder(List<Integer> shoppingCartIds);
	public List<ShoppingCart> shoppingCarts(int customer_id);
}
