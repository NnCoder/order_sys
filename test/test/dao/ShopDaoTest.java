package test.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.order.dao.ShopDao;
import com.order.model.Shop;

public class ShopDaoTest {
	private final ApplicationContext atx;
	private ShopDao shopDao;
	
	public ShopDaoTest(){
		atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		shopDao = atx.getBean(ShopDao.class);
	}
	
	@Test
	public void testGetByPhone(){
		Shop shop = shopDao.getByPhoneNum("123456");
		System.out.println(shop);
	}
}
