package com.order.model;

import javax.persistence.*;

@Entity
public class Food {
	private int id;
	private String name;
	private String imgSrc;
	private Shop shop;
	private String price;
	private String description;
	private int shop_id;
	
	public Food() {}
	
	
	public Food(int id, String name, String imgSrc, String price, String description) {
		super();
		this.id = id;
		this.name = name;
		this.imgSrc = imgSrc;
		this.price = price;
		this.description = description;
	}

	@Transient
	public int getShop_id() {
		return shop_id;
	}

	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
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
	
	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
	
}
