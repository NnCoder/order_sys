package com.order.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	
	private int id;
	private String phoneNum;
	private String password;
	private String name;
	private String address;
	private Set<Ordeer> orders;
	private Set<ShoppingCart> shoppingCart;
	
	public Customer() {
	}
	
	public Customer(int id,String address,String phoneNum,String password,String name){
		this.id = id;
		this.address = address;
		this.phoneNum = phoneNum;
		this.password = password;
		this.name = name;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(fetch=FetchType.LAZY)
	public Set<Ordeer> getOrders() {
		return orders;
	}
	public void setOrders(Set<Ordeer> orders) {
		this.orders = orders;
	}
	@OneToMany(fetch=FetchType.LAZY,mappedBy="customer")
	public Set<ShoppingCart> getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(Set<ShoppingCart> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
