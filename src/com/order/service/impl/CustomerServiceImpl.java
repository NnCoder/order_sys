package com.order.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.dao.CustomerDao;
import com.order.model.Customer;
import com.order.model.Food;
import com.order.model.Ordeer;
import com.order.model.ShoppingCart;
import com.order.service.CustomerService;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDao customerDao;

	
	@Override
	@Transactional(readOnly=true)
	public Customer getByPhoneNum(Customer customer) {
		Customer c = customerDao.getByPhoneNum(customer.getPhoneNum());
		return c;
	}

	@Override
	@Transactional(readOnly=true)
	public Customer getById(Customer customer) {
		Customer c = customerDao.getById(customer.getId());
		return c;
	}

	@Override
	@Transactional(readOnly=true)
	public Set<Ordeer> getOrdeers(Customer customer) {
		Set<Ordeer> ordeers = customerDao.getOrdeersById(customer.getId());
		return ordeers;
	}

	@Override
	@Transactional(readOnly=true)
	public Set<ShoppingCart> getShoppingCarts(Customer customer) {
		Set<ShoppingCart> shoppingCarts = customerDao.getShoppingCartsById(customer.getId());
		return shoppingCarts;
	}

	@Override
	public int update(Customer customer) {
		int status = customerDao.update(customer);
		return status;
	}

	@Override
	public int register(Customer customer) {
		//先看看这个手机号码是不是被注册过了
		if(customerDao.getByPhoneNum(customer.getPhoneNum())==null){
			customerDao.add(customer);
			return 1;
		}
		return 0;
	}

	@Override
	@Transactional(readOnly=true)
	public Set<Ordeer> getOrdeers(Customer customer, int status,int type) {
		if(status == -2){
			return customerDao.getOrdeersById(customer.getId());
		}
		Set<Ordeer> ordeers = customerDao.getOrdeers(customer.getId(), status,type);
		return ordeers;
	}

	@Override
	public int modifyPsw(Customer customer) {
		//psws[0]:用户输入的旧密码  psws[1]:新密码
		String psws[] = customer.getPassword().split(",");
		//取得旧密码
		String oldPsw = customerDao.getById(customer.getId()).getPassword();
		if(!psws[0].equals(oldPsw))
			return 0;
		
		return customerDao.updatePswById(customer.getId(),psws[1]);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Customer> getAll() {
		return customerDao.getAll();
	}
	
}
