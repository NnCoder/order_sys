package com.order.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.order.model.Customer;
import com.order.model.Ordeer;
import com.order.service.CustomerService;

@Controller
@Scope("prototype")
public class CustomerAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1929411296115697947L;
	private File img;
	private String imgFileName;
	private String imgFileContentType;
	@Autowired
	private CustomerService customerService;
	private Customer customer;
	private String strMsg;
	private int order_type;
	private List<Customer> customers;
	private Map<String,Object> session;
	private Map<String,Object> result;

	/**
	 * 顾客注册 黄海雯
	 * @return
	 */
	public String register(){
		//调用service进行注册，获取注册成功与否的code，0为失败，可能是出BUG了，1为成功
		int code = customerService.register(customer);
		result = new HashMap<String, Object>();
		result.put("code", code);
		return "json";
	}

	/**
	 * 管理员获取所有注册用户信息 吴凯文
	 * @return
			 */
	public String queryAll(){
		customers = customerService.getAll();
		return SUCCESS;
	}
	
	public String ordersNot() {
		int id = (int) session.get("customer_id");
		customer = new Customer();
		customer.setId(id);
		Set<Ordeer> ordeers = customerService.getOrdeers(customer,0,order_type);
		customer.setOrders(ordeers);
		return SUCCESS;
	}

	public String ordersAccess() {
		int id = (int) session.get("customer_id");
		customer = new Customer();
		customer.setId(id);
		Set<Ordeer> ordeers = customerService.getOrdeers(customer,1,order_type);
		customer.setOrders(ordeers);
		return SUCCESS;
	}

	public String ordersBack() {
		int id = (int) session.get("customer_id");
		customer = new Customer();
		customer.setId(id);
		Set<Ordeer> ordeers = customerService.getOrdeers(customer,-1,order_type);
		customer.setOrders(ordeers);
		return SUCCESS;
	}

	public String ordersComplete() {
		int id = (int) session.get("customer_id");
		customer = new Customer();
		customer.setId(id);
		Set<Ordeer> ordeers = customerService.getOrdeers(customer,99,order_type);
		customer.setOrders(ordeers);
		return SUCCESS;
	}
	
	public String query(){
		int id = (int) session.get("customer_id");
		customer = new Customer();
		customer.setId(id);
		customer = customerService.getById(customer);
		result = new HashMap<String, Object>();
		result.put("customer_name",customer.getName());
		result.put("customer_id",customer.getId());
		return "json";
	}
	
	public String updateInput(){
		int id = (int) session.get("customer_id");
		customer = new Customer();
		customer.setId(id);
		customer = customerService.getById(customer);
		return SUCCESS;
	}
	
	public String update(){
		int id = (int) session.get("customer_id");
		customer.setId(id);
		int code = customerService.update(customer);
		result = new HashMap<String, Object>();
		result.put("code", code);
		return "json";
		
	}
	
	public String modifyPsw(){
		int id = (int) session.get("customer_id");
		customer.setId(id);
		int code = customerService.modifyPsw(customer);
		result = new HashMap<String, Object>();
		result.put("code",code);
		return "json";
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getImgFileContentType() {
		return imgFileContentType;
	}

	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String,Object> getResult() {
		return result;
	}

	public void setResult(Map<String,Object> result) {
		this.result = result;
	}

	public String getStrMsg() {
		return strMsg;
	}

	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public int getOrder_type() {
		return order_type;
	}

	public void setOrder_type(int order_type) {
		this.order_type = order_type;
	}

	
}
