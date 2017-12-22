package com.order.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.order.model.Food;
import com.order.model.Shop;
import com.order.service.FoodService;
import com.order.util.FileUtils;

@Controller
@Scope("prototype")
public class FoodAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9097844788797275130L;
	private Map<String,Object> session;
	private Map<String,Object> result;
	private String msg;
	private File img;
	private String imgFileName;
	private String imgFileContentType;
	private Food food;
	private List<Food> foods;
	private int shop_id;
	@Autowired
	private FoodService foodService;

	public String queryByKey(){
		foods = foodService.getByKey(msg,shop_id);
		return SUCCESS;
	}

	public String add() throws IOException{
		//获取上传图片
		String path = "/imgSrc/";
		String uploadPath = ServletActionContext.getServletContext().getInitParameter("uploadPath")+path;
		String accessPath = ServletActionContext.getServletContext().getInitParameter("accessPath")+path;
		File toFile = new File(uploadPath,imgFileName);
		File file = FileUtils.copyFile(img, toFile);
		
		int shop_id = (int) session.get("shop_id");
		Shop shop = new Shop();
		shop.setId(shop_id);
		String imgSrc = accessPath+file.getName();
		food.setImgSrc(imgSrc);
		food.setShop(shop);
		foodService.add(food);
		return SUCCESS;
	}
	
	public String updateInput(){
		food = foodService.get(food);
		return SUCCESS;
	}
	
	public String toOrder(){
		food = foodService.get(food);
		return SUCCESS;
	}
	
	//更新问题:若无更新头像依旧复制问题。
	public String update() throws IOException{

		result = new HashMap<String,Object>();

		int status = foodService.update(food);
		result.put("status",status);
		return "json";
	}
	
	public String delete(){
		int code = foodService.delete(food);
		result = new HashMap<String, Object>();
		result.put("code",code);
		return "json";
	}
	
	public String query(){
		food = foodService.get(food);
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
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
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}

	public Map<String,Object> getResult() {
		return result;
	}

	public void setResult(Map<String,Object> result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getShop_id() {
		return shop_id;
	}

	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}
}
