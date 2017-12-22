package com.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.dao.CustomerDao;
import com.order.dao.RootDao;
import com.order.dao.ShopDao;
import com.order.model.Customer;
import com.order.model.Root;
import com.order.model.Shop;
import com.order.service.LoginService;


@Service("loginService")
@Transactional(readOnly=true)
public class LoginServiceImpl implements LoginService{
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private RootDao rootDao;
	
	@Override
	public int customerLogin(Customer customer) {
		//通过顾客的手机号获取它的密码
		Customer customerTemp = 
				customerDao.getByPhoneNum(customer.getPhoneNum());
		//如果能够获取到
		if(customerTemp!=null){
			//看看密码对不对
			if(customer.getPassword().equals(customerTemp.getPassword())){
				return customerTemp.getId();
			}
			return -1;
		}
		//账号不存在
		return 0;
	}

	@Override
	public int shopLogin(Shop shop) {
		//商家的手机号来得到商家的信息
		Shop shopTemp = 
				shopDao.getByPhoneNum(shop.getPhoneNum());
		//不为空，就是说这个手机号是存在的
		if(shopTemp!=null){
			//看看他是否已通过管理员的审核
			if(shopDao.getStatusByPhoneNum(shop.getPhoneNum())==0){
				return -2;
			}
			//看看他密码对不对!
			if(shop.getPassword().equals(shopTemp.getPassword())){
				return shopTemp.getId();
			}
			return -1;
		}
		//手机号不存在
		return 0;
	}

	@Override
	public int rootLogin(Root root) {
		try {
			//通过用户名获取密码
			Root r = rootDao.getByUN(root.getUsername());
			if(root.getPassword().equals(r.getPassword())){
				return r.getId();
			}else{
				return -1;
			}
		} catch (EmptyResultDataAccessException e) {
			//空结果集，就是没有该用户名
			return 0;
		}
	}
	
}
