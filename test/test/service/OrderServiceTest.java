package test.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.order.model.Customer;
import com.order.model.Food;
import com.order.model.Ordeer;
import com.order.model.Shop;
import com.order.service.OrdeerService;
import com.order.service.ShopService;

public class OrderServiceTest {
	private final ApplicationContext atx;
	private OrdeerService orderService;
	
	public OrderServiceTest(){
		atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		orderService = atx.getBean(OrdeerService.class);
	}
	
	@Test
	public void testAdd(){
		Ordeer ordeer = new Ordeer();
		Customer customer = new Customer();
		customer.setId(1);
		Food food = new Food();
		food.setId(1);
		Shop shop = new Shop();
		shop.setId(1);
		ordeer.setCustomer(customer);
		ordeer.setFood(food);
		ordeer.setShop(shop);
		orderService.add(ordeer);
	}
}
