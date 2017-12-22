package com.order.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.order.dao.CustomerDao;
import com.order.model.Customer;
import com.order.model.Food;
import com.order.model.Ordeer;
import com.order.model.Shop;
import com.order.model.ShoppingCart;
import com.order.rowMapper.CustomerRowMapper;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl implements CustomerDao{

	/**
	 * 顾客登录时用
	 * @param phoneNum 手机号
	 * @return 顾客信息，id,密码，手机号
	 */
	@Override
	public Customer getByPhoneNum(String phoneNum) {

		String sql = "select id,password,phoneNum,name,address from customer where phoneNum=?";
		try {
			//执行sql语句
			Customer customer = 
					getJdbcTemplate().queryForObject(sql,new CustomerRowMapper(),phoneNum);
			return customer;
		} catch (EmptyResultDataAccessException e) {
			//空的结果，通过手机号找不到该顾客信息
			return null;
		}
		
	}
	
	@Override
	public Set<Ordeer> getOrdeersById(int customer_id) {
		String sql = "select ordeer.id,food.name,food.price,orderTime,arriveTime,status,total,customer.name,shop.name,shop.phoneNum "
				+ "from "
				+ "ordeer left join customer on ordeer.customer_id=customer.id "
				+ "left join shop on ordeer.shop_id=shop.id "
				+ "left join food on food.id=ordeer.food_id"
				+ "where ordeer.customer_id=?";
		List<Map<String,Object>> resultList = 
				getJdbcTemplate().queryForList(sql,customer_id);
		
		HashSet<Ordeer> ordeerSet = new HashSet<Ordeer>();
		for(Map<String,Object> result:resultList){
			Ordeer ordeer = new Ordeer();
			Customer customer = new Customer();
			Shop shop = new Shop();
			
			//获取客户信息
			String customer_name = (String) result.get("customer.name");
			customer.setName(customer_name);
			//获取商店信息
			String shop_name = (String) result.get("shop.name");
			String shop_phoneNum = (String) result.get("shop.phoneNum");
			shop.setName(shop_name);
			shop.setPhoneNum(shop_phoneNum);
			//获取订单基本信息
			int order_id = (int) result.get("ordeer.id");
			int order_status = (int) result.get("status");
			Date orderTime = (Date) result.get("orderTime");
			Date arriveTime = (Date) result.get("arriveTime");
			ordeer.setId(order_id);
			ordeer.setStatus(order_status);
			ordeer.setOrderTime(orderTime);
			ordeer.setArriveTime(arriveTime);
			
			//整合
			ordeer.setCustomer(customer);
			ordeer.setCustomer(customer);
			ordeer.setShop(shop);
			
			//添加到结果集中
			ordeerSet.add(ordeer);
		}
		return ordeerSet;
	}

	@Override
	public Set<ShoppingCart> getShoppingCartsById(int customer_id) {
		String sql = "select sc.id,food.name,food.imgSrc,shop.name,shop.phoneNum"
				+ " from shoppingcart as sc left join food on sc.food_id=food.id"
				+ " left join shop on sc.shop_id=shop.id"
				+ " where customer_id=?";
		
		List<Map<String,Object>> resultList = 
				getJdbcTemplate().queryForList(sql,customer_id);
		
		HashSet<ShoppingCart> shoppingCartSet = new HashSet<ShoppingCart>();
		
		for(Map<String,Object> result:resultList){
			ShoppingCart shoppingCart = new ShoppingCart();
			Food food = new Food();
			Shop shop = new Shop();
			
			//获取食品信息
			String food_name = (String) result.get("food.name");
			String food_imgSrc = (String) result.get("food.imgSrc");
			food.setName(food_name);
			food.setImgSrc(food_imgSrc);
			//获取商店信息
			String shop_name = (String)result.get("shop.name");
			String shop_phoneNum = (String)result.get("shop.phoneNum");
			shop.setName(shop_name);
			shop.setPhoneNum(shop_phoneNum);
			//获取购物车信息
			int shoppingCart_id = (int)result.get("sc.id");
			shoppingCart.setId(shoppingCart_id);
			//整合
			shoppingCart.setFood(food);
			shoppingCart.setShop(shop);
			
			//添加到结果集中
			shoppingCartSet.add(shoppingCart);
		}
		return shoppingCartSet;
	}

	@Override
	public Customer getById(int customer_id) {
		String sql = "select * from customer where id=?";
		Customer customer = 
				getJdbcTemplate().queryForObject(sql,new CustomerRowMapper(),customer_id);
		return customer;
	}

	@Override
	public int add(Customer customer) {
		String sql = "insert into customer(name,phoneNum,password,address) values(?,?,?,?)";
		int status = 
				getJdbcTemplate().update(sql,customer.getName(),customer.getPhoneNum(),customer.getPassword(),customer.getAddress());
		return status;
	}

	@Override
	public int update(Customer customer) {
		String sql = "update customer set name=? where id=?";
		int status = 
				getJdbcTemplate().update(sql,customer.getName(),customer.getId());
		return status;
	}

	@Override
	public Set<Ordeer> getOrdeers(int customer_id, int status,int type) {
		String sql = "select ordeer.id,"
				+ "orderTime,"
				+ "arriveTime,"
				+ "remarks,"
				+ "ordeer.status as status,"
				+ "food.id as food_id,"
				+ "food.name as food_name,"
				+ "food.price as food_price,"
				+ "food.imgSrc as food_imgSrc,"
				+ "customer.name as customer_name,"
				+ "customer.phoneNum as customer_phoneNum,"
				+ "shop.id as shop_id,"
				+ "shop.name as shop_name,"
				+ "shop.phoneNum as shop_phoneNum,"
				+ "shop.imgSrc as shop_imgSrc,"
				+ "shop.address as shop_address "
				+ "from "
				+ "ordeer left join customer on ordeer.customer_id=customer.id "
				+ "left join food on ordeer.food_id=food.id "
				+ "left join shop on ordeer.shop_id=shop.id "
				+ "where ordeer.customer_id=? and ordeer.type=? ";
		//顺带查看已评论的订单
		if(status==99){
			sql+="and (ordeer.status=?  or ordeer.status=100) and to_days(arriveTime) = to_days(now())";
		}else{
			sql+="and ordeer.status=?";
		}

		List<Map<String,Object>> resultList = 
				getJdbcTemplate().queryForList(sql,customer_id,type,status);
		
		
		HashSet<Ordeer> ordeerSet = new HashSet<Ordeer>();
		if(resultList.isEmpty()){
			return ordeerSet;
		}
		for(Map<String,Object> result:resultList){
			Ordeer ordeer = new Ordeer();
			Customer customer = new Customer();
			Shop shop = new Shop();
			Food food = new Food();
			int food_id = (int)result.get("food_id");
			String food_name = (String)result.get("food_name");
			String food_price = result.get("food_price").toString();
			String food_imgSrc = (String)result.get("food_imgSrc");
			food.setId(food_id);
			food.setName(food_name);
			food.setPrice(food_price);
			food.setImgSrc(food_imgSrc);
			//获取客户信息
			String customer_name = (String) result.get("customer_name");
			String customer_phoneNum = (String) result.get("customer_phoneNum");
			customer.setName(customer_name);
			customer.setPhoneNum(customer_phoneNum);
			//获取商店信息
			int shop_id = (int)result.get("shop_id");
			String shop_imgSrc = (String) result.get("shop_imgSrc");
			String shop_name = (String) result.get("shop_name");
			String shop_phoneNum = (String) result.get("shop_phoneNum");
			String shop_address = (String)result.get("shop_address");
			shop.setId(shop_id);
			shop.setImgSrc(shop_imgSrc);
			shop.setName(shop_name);
			shop.setPhoneNum(shop_phoneNum);
			shop.setAddress(shop_address);
			//获取订单基本信息
			int order_id = (int) result.get("id");
			int order_status = (int) result.get("status");
			Date orderTime = new Date(((Timestamp) result.get("orderTime")).getTime());
			//Date arriveTime = new Date(((Timestamp) result.get("arriveTime")).getTime());
			String remarks = (String)result.get("remarks");
			ordeer.setId(order_id);
			ordeer.setStatus(order_status);
			ordeer.setOrderTime(orderTime);
			//ordeer.setArriveTime(arriveTime);
			ordeer.setRemarks(remarks);
			ordeer.setType(type);
			
			//整合
			ordeer.setCustomer(customer);
			ordeer.setCustomer(customer);
			ordeer.setShop(shop);
			ordeer.setFood(food);
			//添加到结果集中
			ordeerSet.add(ordeer);
		}
		return ordeerSet;
	}
	@Override
	public Set<Food> getFoods(int order_id) {
		String sql = "select name from food left join ordeer_food on food.id=ordeer_food.id where ordeer.id=?";
		List<Map<String,Object>> rs = getJdbcTemplate().queryForList(sql, order_id);
		Food food = null;
		Set<Food> foods = new LinkedHashSet<Food>();
		for(Map<String,Object> map:rs){
			food = new Food();
			food.setName((String)map.get("name"));
		}
		return foods;
	}
	
	@Override
	public int updatePswById(int id,String newPsw) {
		String sql = "update customer set password=? where id=?";
		int code = getJdbcTemplate().update(sql,newPsw,id);
		return code;
	}

	@Override
	public List<Customer> getAll() {
		String sql = "select * from customer";
		List<Customer> customers = new ArrayList<Customer>();
		List<Map<String,Object>> results =  getJdbcTemplate().queryForList(sql);
		for(Map<String,Object> rs : results){
			int id = (int) rs.get("id");
			String phoneNum = (String) rs.get("phoneNum");
			String password = (String) rs.get("password");
			String name = (String) rs.get("name");
			String address = (String)rs.get("address");
			Customer customer = new Customer(id,address,phoneNum, password, name);
			customers.add(customer);
		}
		return customers;
	}

}
