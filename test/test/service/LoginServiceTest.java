package test.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.order.model.Shop;
import com.order.service.LoginService;

public class LoginServiceTest {
	private final ApplicationContext atx;
	private LoginService loginService;
	public LoginServiceTest(){
		atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		loginService = atx.getBean(LoginService.class);
	}
	
	@Test
	public void testShopLogin(){
		Shop shop = new Shop();
		shop.setPhoneNum("123456");
		shop.setPassword("123");
		
		int status = loginService.shopLogin(shop);
		System.out.println(status);
	}
}
