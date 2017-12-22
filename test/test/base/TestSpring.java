package test.base;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.interceptor.annotations.Before;

public class TestSpring {
	private final ApplicationContext atx;
	
	public TestSpring() {
		atx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Before
	public void init(){
	}
	
	@Test
	public void test(){
		SessionFactory sessionFactory = atx.getBean(SessionFactory.class);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.getTransaction().commit();
	}
}
