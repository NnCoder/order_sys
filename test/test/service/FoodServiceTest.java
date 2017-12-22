package test.service;

import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.order.model.Food;
import com.order.model.Ordeer;

public class ShopServiceTest {
	private final ApplicationContext atx;
	private FoodService foodService;
	public ShopServiceTest(){
		atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		foodService = atx.getBean(FoodService.class);
	}
	
	@Test
	public void testGetOrdeer(){
		Shop shop = new Shop();
		shop.setId(1);
		Set<Ordeer> ordeers = shopService.getOrdeers(shop,1,0);
	}
	
	@Test
	public void testGetFoods(){
		Shop shop = new Shop();
		shop.setId(1);
		Set<Food> set = shopService.getFoods(shop);
		for(Food food:set){
			System.out.println(food.getName());
		}
	}
	
	@Test
	public void testGetByPhoneNum(){
		Shop shop = new Shop();
		shop.setPhoneNum("123456");
		if(shopService.getByPhoneNum(shop)==null){
			System.out.println("null");
		}
	}
	
	@Test
	public void testRegister(){
		Shop shop = new Shop();
		shop.setPhoneNum("13755");
		shop.setPassword("123");
		shopService.register(shop);
	}
	
	@Test
	public void testUpdate(){
		Shop shop = new Shop();
		shop.setId(1);
		shop.setImgSrc("/ord_upload/imgSrc/timg.jpg");
		shop.setName("测试");
		shop.setAddress("吉林大学珠海学院");
		shop.setDescription("test");
		shopService.update(shop);
	}
}
