package com.order.dao;

import java.util.List;
import java.util.Set;

import com.order.model.Customer;
import com.order.model.Food;
import com.order.model.Ordeer;
import com.order.model.ShoppingCart;


public interface CustomerDao {
	public Customer getById(int customer_id);
	public Customer getByPhoneNum(String phoneNum);
	public List<Customer> getAll();
	public int add(Customer customer);
	public int update(Customer customer);
	public int updatePswById(int id,String newPsw);
	public Set<Ordeer> getOrdeersById(int customer_id);
	public Set<Ordeer> getOrdeers(int customer_id,int status,int type);
	public Set<Food> getFoods(int order_id);
 	public Set<ShoppingCart> getShoppingCartsById(int customer_id);
}
