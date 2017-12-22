package com.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.dao.ShoppingCartDao;
import com.order.model.ShoppingCart;
import com.order.service.ShoppingCartService;

@Service("shoppingCartService")
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService{

	@Autowired
	private ShoppingCartDao shoppingCartDao;
	
	@Override
	public int delete(List<Integer> shoppingCartIds) {
		return 0;
	}

	@Override
	public int delete(ShoppingCart shoppingCart) {
		int status = shoppingCartDao.deleteById(shoppingCart.getId());
		return status;
	}

	@Override
	public int add(ShoppingCart shoppingCart) {
		int status = shoppingCartDao.add(shoppingCart);
		return status;
	}

	@Override
	public int addToOrder(List<Integer> shoppingCartIds) {
		shoppingCartDao.addToOrder(shoppingCartIds);
		shoppingCartDao.deleteByIds(shoppingCartIds);
		return 1;
	}

	@Override
	public List<ShoppingCart> shoppingCarts(int customer_id) {
		return shoppingCartDao.getByCustomerId(customer_id);
	}
}
