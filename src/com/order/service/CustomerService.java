package com.order.service;

import java.util.List;
import java.util.Set;

import com.order.model.Customer;
import com.order.model.Ordeer;
import com.order.model.ShoppingCart;

public interface CustomerService {
	public Customer getByPhoneNum(Customer customer);
	public List<Customer> getAll();
	public Customer getById(Customer customer);
	public int update(Customer customer);
	public int register(Customer customer);
	public int modifyPsw(Customer customer);
	public Set<Ordeer> getOrdeers(Customer customer);
	public Set<Ordeer> getOrdeers(Customer customer,int status,int type);
	public Set<ShoppingCart> getShoppingCarts(Customer customer);
}
