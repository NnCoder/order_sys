package com.order.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.order.dao.ShopDao;
import com.order.model.Customer;
import com.order.model.Food;
import com.order.model.Ordeer;
import com.order.model.Shop;
import com.order.rowMapper.ShopRowMapper;

@Repository("shopDao")
public class ShopDaoImpl extends BaseDaoImpl implements ShopDao{

	/**
	 * 通过手机获取商家的状态，看看他是不是审核通过了
	 * @param shop_phoneNum
	 * @return
	 */
	public int getStatusByPhoneNum(String shop_phoneNum){
		String sql = "select status from shop where phoneNum=?";
		Integer status = getJdbcTemplate().queryForObject(sql, Integer.class,shop_phoneNum);
		//返回获取的status
		return status;
	}

	/**
	 * 通过手机号码来得到商家的信息
	 * @param shop_phoneNum
	 * @return
	 */
	@Override
	public Shop getByPhoneNum(String shop_phoneNum) {
		String sql = "select * from shop where phoneNum=?";
		try{
			//查询商家
			Shop shop = getJdbcTemplate().queryForObject(sql,new ShopRowMapper(),shop_phoneNum);
			return shop;
		}catch(EmptyResultDataAccessException e){
			return null;
		}
		
	}

	@Override
	public Shop getById(int shop_id) {
		String sql = "select * from shop where id=?";
		Shop shop = getJdbcTemplate().queryForObject(sql,new ShopRowMapper(),shop_id);
		return shop;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Food> getFoodsById(int shop_id) {
		String hql = "select shop.foods from Shop shop where shop.id=:shop_id";
		Query query = getSession().createQuery(hql);
		query.setInteger("shop_id", shop_id);
		
		LinkedHashSet<Food> foods = new LinkedHashSet<Food>(query.list());
		return foods;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public Set<Ordeer> getOrdeersById(int shop_id) {
		String hql = "select shop.ordeers from Shop shop where shop.id=:shop_id";
		Query query = getSession().createQuery(hql);
		query.setInteger("shop_id",shop_id);
		
		LinkedHashSet<Ordeer> ordeers = new LinkedHashSet<Ordeer>(query.list());
		return ordeers;
	}

	@Override
	public Set<Ordeer> getOrdeers(int shop_id, int status,int type) {
		String sql = "select ordeer.id,"
				+ "orderTime,"
				+ "arriveTime,"
				+ "remarks,"
				+ "ordeer.status as status,"
				+ "food.name as food_name,"
				+ "food.price as food_price,"
				+ "food.imgSrc as food_imgSrc,"
				+ "customer.id as customer_id,"
				+ "customer.name as customer_name,"
				+ "customer.phoneNum as customer_phoneNum,"
				+ "customer.address as customer_address,"
				+ "shop.name as shop_name,"
				+ "shop.phoneNum as shop_phoneNum,"
				+ "shop.imgSrc as shop_imgSrc "
				+ "from "
				+ "ordeer left join customer on ordeer.customer_id=customer.id "
				+ "left join food on ordeer.food_id=food.id "
				+ "left join shop on ordeer.shop_id=shop.id "
				+ "where ordeer.shop_id=? and ordeer.status=? and ordeer.type=? ";
		if(status==99){
			sql+="or ordeer.status=100";
		}
		List<Map<String,Object>> resultList = 
				getJdbcTemplate().queryForList(sql,shop_id,status,type);
		
		
		LinkedHashSet<Ordeer> ordeerSet = new LinkedHashSet<Ordeer>();
		if(resultList.isEmpty()){
			return ordeerSet;
		}
		for(Map<String,Object> result:resultList){
			Ordeer ordeer = new Ordeer();
			Customer customer = new Customer();
			Shop shop = new Shop();
			Food food = new Food();
			
			String food_name = (String)result.get("food_name");
			String food_price = result.get("food_price").toString();
			String food_imgSrc = (String)result.get("food_imgSrc");
			food.setName(food_name);
			food.setPrice(food_price);
			food.setImgSrc(food_imgSrc);
			//获取客户信息
			String customer_name = (String) result.get("customer_name");
			String customer_phoneNum = (String) result.get("customer_phoneNum");
			String customer_address = (String) result.get("customer_address");
			int customer_id = (int)result.get("customer_id");
			customer.setId(customer_id);
			customer.setName(customer_name);
			customer.setPhoneNum(customer_phoneNum);
			customer.setAddress(customer_address);
			//获取商店信息
			String shop_name = (String) result.get("shop_name");
			String shop_phoneNum = (String) result.get("shop_phoneNum");
			shop.setName(shop_name);
			shop.setPhoneNum(shop_phoneNum);
			//获取订单基本信息
			int order_id = (int) result.get("id");
			int order_status = (int) result.get("status");
			Date orderTime = (Date) result.get("orderTime");
			Date arriveTime = (Date) result.get("arriveTime");
			String remarks = (String)result.get("remarks");

			ordeer.setId(order_id);
			ordeer.setStatus(order_status);
			ordeer.setOrderTime(orderTime);
			ordeer.setArriveTime(arriveTime);
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
	public Set<Ordeer> getTurnover(int shop_id, int recent_day) {
		String sql =  "select ordeer.id as order_id,"
				+ "orderTime,"
				+ "food.name as food_name,"
				+ "food.price as food_price "
				+ "from "
				+ "ordeer "
				+ "left join food on ordeer.food_id=food.id "
				+ "where ordeer.shop_id=? and (ordeer.status=99 or ordeer.status=100) ";
		if(recent_day==1){
			sql += "and to_days(orderTime) = to_days(now())";
		}
		else{
			sql += "and date_sub(curdate(), INTERVAL "+recent_day+" DAY) <= date(orderTime);";
		}
		LinkedHashSet<Ordeer> ordeers = new LinkedHashSet<>();
		List<Map<String,Object>> result = getJdbcTemplate().queryForList(sql,shop_id);
		Food food = null;
		Ordeer ordeer = null;
		for(Map<String,Object> rs:result){
			int order_id = (int) rs.get("order_id");
			Timestamp orderTimestap = (Timestamp)rs.get("orderTime");
			String food_name = (String) rs.get("food_name");
			String food_price = rs.get("food_price").toString();
			ordeer = new Ordeer();
			food = new Food();
			ordeer.setId(order_id);
			ordeer.setOrderTime(new Date(orderTimestap.getTime()));
			food.setName(food_name);
			food.setPrice(food_price);
			ordeer.setFood(food);
			ordeers.add(ordeer);
		}
		return ordeers;
	}

	@Override
	public int add(Shop shop) {
		String sql = "insert into shop(name,address,idcard,phoneNum,password,description,imgSrc) values(?,?,?,?,?,?,?)";
		int status = 
				getJdbcTemplate().update(
						sql,
						shop.getName(),
						shop.getAddress(),
						shop.getIdcard(),
						shop.getPhoneNum(),
						shop.getPassword(),
						shop.getDescription(),
						shop.getImgSrc());
		return status;
	}

	@Override
	public int update(Shop shop) {
		String sql = "update shop set name=?,address=?,description=? where id=?";
		int status = 
				getJdbcTemplate().update(sql,shop.getName(),shop.getAddress(),shop.getDescription(),shop.getId());
		
		return status;
	}

	@Override
	public List<Shop> getAll(int status) {
		String sql = "select id,name,description,address,imgSrc,phoneNum from shop where status=?";
		List<Map<String,Object>> result = getJdbcTemplate().queryForList(sql,status);
		List<Shop> shops = new ArrayList<Shop>();
		for(Map<String,Object> rs:result){
			int id = (int) rs.get("id");
			String name = (String) rs.get("name");
			String description = (String) rs.get("description");
			String imgSrc = (String) rs.get("imgSrc");
			String phoneNum = (String) rs.get("phoneNum");
			String address = (String) rs.get("address");
			String idcard = (String)rs.get("idcard");
			Shop shop = new Shop(id,idcard,address, phoneNum,"", name, description, imgSrc);
			shops.add(shop);
		}
		return shops;
	}

	@Override
	public int updatePswById(int id, String newPsw) {
		String sql = "update shop set password=? where id=?";
		int code = getJdbcTemplate().update(sql,newPsw,id);
		return code;
	}

	@Override
	public int updateStatusById(int id, int status) {
		String sql = "update shop set status=? where id=?";
		return getJdbcTemplate().update(sql, status,id);
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
	public List<Shop> getByKey(String message){
		String sql = "select id,name,description,address,imgSrc,phoneNum from shop where name like ? and status=1";
		List<Map<String,Object>> result = getJdbcTemplate().queryForList(sql,"%"+message+"%");
		List<Shop> shops = new ArrayList<Shop>();
		for(Map<String,Object> rs:result){
			int id = (int) rs.get("id");
			String name = (String) rs.get("name");
			String description = (String) rs.get("description");
			String imgSrc = (String) rs.get("imgSrc");
			String phoneNum = (String) rs.get("phoneNum");
			String address = (String) rs.get("address");
			String idcard = (String)rs.get("idcard");
			Shop shop = new Shop(id,idcard,address, phoneNum,"", name, description, imgSrc);
			shops.add(shop);
		}
		return shops;
	}

	@Override
	public List<Shop> getOpenshops() {
		String sql = "select id,name,description,address,imgSrc,phoneNum from shop where status=1 and open=1";
		List<Map<String,Object>> result = getJdbcTemplate().queryForList(sql);
		List<Shop> shops = new ArrayList<Shop>();
		for(Map<String,Object> rs:result){
			int id = (int) rs.get("id");
			String name = (String) rs.get("name");
			String description = (String) rs.get("description");
			String imgSrc = (String) rs.get("imgSrc");
			String phoneNum = (String) rs.get("phoneNum");
			String address = (String) rs.get("address");
			String idcard = (String)rs.get("idcard");
			Shop shop = new Shop(id,idcard,address, phoneNum,"", name, description, imgSrc);
			shops.add(shop);
		}
		return shops;
	}

	@Override
	public int updateOpen(int shop_id, int open) {
		String sql = "update shop set open=? where id=?";
		return getJdbcTemplate().update(sql,open,shop_id);
	}
}
