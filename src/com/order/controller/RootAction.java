package com.order.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.order.model.Root;
import com.order.service.RootService;

@Controller
@Scope("prototype")
public class RootAction implements SessionAware{
	private Root root;
	@Autowired
	private RootService rootService;
	private Map<String,Object> result;
	private Map<String,Object> session;

	/**
	 * 超级管理员修改密码
	 * @return
	 */
	public String modifyPsw(){
		int rootId = (int) session.get("root_id");
		root.setId(rootId);
		int code = rootService.modifyPsw(root);
		result = new HashMap<String, Object>();
		result.put("code", code);
		return "json";
	}
	
	public Root getRoot() {
		return root;
	}


	public void setRoot(Root root) {
		this.root = root;
	}


	public RootService getRootService() {
		return rootService;
	}


	public void setRootService(RootService rootService) {
		this.rootService = rootService;
	}


	public Map<String, Object> getResult() {
		return result;
	}


	public void setResult(Map<String, Object> result) {
		this.result = result;
	}


	public Map<String, Object> getSession() {
		return session;
	}


	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
}
