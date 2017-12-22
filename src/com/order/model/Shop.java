package com.order.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Shop {
	private int id;
	private String phoneNum;
	private String password;
	private String name;
	private String address;
	private String description;
	private String imgSrc;
	private String idcard;
	//0为关门,1为开门
	private int open;
	private Set<Food> foods;
	private Set<Ordeer> ordeers;
	
	public Shop(){};
	
	
	public Shop(int id,String idcard, String address,String phoneNum, String password, String name, String description, String imgSrc) {
		super();
		this.id = id;
		this.setIdcard(idcard);
		this.phoneNum = phoneNum;
		this.password = password;
		this.name = name;
		this.address = address;
		this.description = description;
		this.imgSrc = imgSrc;
	}


	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(mappedBy="shop")
	public Set<Ordeer> getOrdeers() {
		return ordeers;
	}


	public void setOrdeers(Set<Ordeer> ordeers) {
		this.ordeers = ordeers;
	}

	@OneToMany(mappedBy="shop")
	public Set<Food> getFoods() {
		return foods;
	}


	public void setFoods(Set<Food> foods) {
		this.foods = foods;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getIdcard() {
		return idcard;
	}


	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}


	public int getOpen() {
		return open;
	}

	public void setOpen(int open) {
		this.open = open;
	}
}
