package com.order.service;

import com.order.model.Customer;
import com.order.model.Root;
import com.order.model.Shop;

public interface LoginService {
	public int customerLogin(Customer customer);
	public int shopLogin(Shop shop);
	public int rootLogin(Root root);
}
