package com.order.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.order.model.Customer;
import com.order.model.Ordeer;
import com.order.service.OrdeerService;

@Controller
@Scope("prototype")
public class OrdeerAction extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 32184575931897903L;
	@Autowired
	private OrdeerService ordeerService;
	private Ordeer ordeer;
	private Map<String,Object> result;
	private Map<String,Object> session;
	
	/**
	 * 添加订单
	 */
	public String placeOrder(){
		int customer_id = (int)session.get("customer_id");
		Customer customer = new Customer();
		customer.setId(customer_id);
		ordeer.setCustomer(customer);
		int status = ordeerService.add(ordeer);
		
		//返回json数据
		result = new HashMap<String, Object>();
		result.put("status", status);
		return "json";
	}
	
	public String updateStatus(){
		int status = ordeer.getStatus();
		int code = ordeerService.updateStatus(ordeer,status);
		String msg = "";
		//根据所要改变的status输出msg
		switch(status){
			case 0:msg="已取消退单,订单变为未接单状态";break;
			case 1:msg="已接单!";break;
			case -1:msg="正在退单!";break;
			case 99:msg="已确认送达!";break;
		}
		result = new HashMap<String, Object>();
		if(code==0)
			msg = "订单已被取消";
		result.put("code",code);
		result.put("msg", msg);
		
		return "json";
	}
	
	public String delete(){
		int code = ordeerService.delete(ordeer);
		result = new HashMap<String, Object>();
		result.put("code", code);
		return "json";
	}
	
	public Ordeer getOrdeer() {
		return ordeer;
	}
	public void setOrdeer(Ordeer ordeer) {
		this.ordeer = ordeer;
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
