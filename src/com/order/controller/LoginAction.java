package com.order.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.order.model.Customer;
import com.order.model.Root;
import com.order.model.Shop;
import com.order.service.LoginService;

@Controller
@Scope("prototype")
public class LoginAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3324219563417701595L;
	
	@Autowired
	private LoginService loginService;
	private Customer customer;
	private Shop shop;
	private Root root;
	private Map<String,Object> result;
	private Map<String,Object> session;

	/**
	 * 黄海雯，顾客登录
	 * @return
	 */
	public String customerLogin(){
		//获取返回的状态码
		int status = loginService.customerLogin(customer);
		result = new HashMap<String, Object>();
		//-1密码错误，0账号不存在
		if(status!=-1&&status!=0){
			//放在session里面
			session.put("customer_id",status);
		}
		result.put("status",status);
		
		return "json";
	}

	/**
	 * 周枫晴，商家登录
	 * @return
	 */
	public String shopLogin(){
		//得到商家登录的status值
		int status = loginService.shopLogin(shop);
		result = new HashMap<String, Object>();
		//大于0说明登录成功
		if(status>0){
			//放在session里面
			session.put("shop_id",status);
		}
		result.put("status",status);
		return "json";
	}

	/**
	 * 超级管理员登录 吴凯文
	 * @return
	 */
	public String rootLogin(){
		int code = loginService.rootLogin(root);
		result = new HashMap<String, Object>();
		if(code!=-1&&code!=0){
			session.put("root_id",code);
		}
		result.put("code", code);
		return "json";
	}
	
	public String shopLogout(){
		session.remove("shop_id");
		return "json";
	}
	
	public String customerLogout(){
		session.remove("customer_id");
		return "json";
	}
	
	
	public Root getRoot() {
		return root;
	}

	public void setRoot(Root root) {
		this.root = root;
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Map<String,Object> getResult() {
		return result;
	}

	public void setResult(Map<String,Object> result) {
		this.result = result;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	
}
