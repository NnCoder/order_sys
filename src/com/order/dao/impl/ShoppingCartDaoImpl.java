package com.order.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.order.model.Customer;
import com.order.model.Food;
import com.order.model.Shop;
import org.springframework.stereotype.Repository;

import com.order.dao.ShoppingCartDao;
import com.order.model.ShoppingCart;

@Repository("shoppingCartDao")
public class ShoppingCartDaoImpl extends BaseDaoImpl implements ShoppingCartDao{

	@Override
	public int deleteById(int cart_id) {
		String sql = "delete from shoppingcart where id=?";
		int status = getJdbcTemplate().update(sql,cart_id);
		return status;
	}

	@Override
	public int add(ShoppingCart shoppingCart) {
		String sql = "insert into shoppingcart(customer_id,food_id,shop_id) values(?,?,?)";
		int status = 
				getJdbcTemplate().update(sql,shoppingCart.getCustomer().getId(),shoppingCart.getFood().getId(),shoppingCart.getShop().getId());
		return status;
	}

	@Override
	public int addToOrder(List<Integer> shoppingCartIds) {
		String sql = "insert into ordeer(orderTime,status,customer_id,food_id,shop_id) "
				+ "values(?,?,"
				+ "(select customer_id from shoppingcart where id=?),"
				+ "(select food_id from shoppingcart where id=?),"
				+ "(select shop_id from shoppingcart where id=?))";
		for(int shoppingCartId:shoppingCartIds){
			getJdbcTemplate().update(sql,new Date(),0,shoppingCartId,shoppingCartId,shoppingCartId);
		}
		return 1;
	}

	@Override
	public int deleteByIds(List<Integer> shoppingCartIds) {
		for(int shoppingCartId:shoppingCartIds){
			deleteById(shoppingCartId);
		}
		return 1;
	}

	@Override
	public List<ShoppingCart> getByCustomerId(int customer_id) {
		String sql = "select customer.name as customer_name," +
				"shoppingcart.id as shoppingcart_id," +
				"food.name as food_name," +
				"food.imgSrc as food_imgSrc," +
				"food.price as food_price," +
				"food.description as food_description," +
				"shoppingcart.shop_id  as shop_id," +
				"shoppingcart.food_id  as food_id " +
				"from shoppingcart left join customer on shoppingcart.customer_id=customer.id " +
				"left join food on shoppingcart.food_id=food.id " +
				"where shoppingcart.customer_id=?";

		List<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>();
		List<Map<String,Object>> rs = getJdbcTemplate().queryForList(sql,customer_id);
		ShoppingCart shoppingCart = null;
		Food food = null;
		Shop shop = null;
		Customer customer = null;
		for(Map<String,Object> result:rs){
			String customer_name = (String)result.get("customer_name");
			String food_description = (String)result.get("food_description");
			int food_id = (int)result.get("food_id");
			String food_name = (String)result.get("food_name");
			String food_imgSrc = (String)result.get("food_imgSrc");
			String food_price = result.get("food_price").toString();
			int shop_id = (int)result.get("shop_id");
			int shoppingcart_id = (int)result.get("shoppingcart_id");
			//初始化顾客
			customer = new Customer();
			customer.setName(customer_name);
			//初始化食物
			food = new Food(food_id,food_name,food_imgSrc,food_price,food_description);
			//初始化商家
			shop = new Shop();
			shop.setId(shop_id);
			//整合到购物车中
			shoppingCart = new ShoppingCart();
			shoppingCart.setId(shoppingcart_id);
			shoppingCart.setCustomer(customer);
			shoppingCart.setFood(food);
			shoppingCart.setShop(shop);
			shoppingCarts.add(shoppingCart);
		}
		return shoppingCarts;
	}
}
