package com.order.controller;

import com.opensymphony.xwork2.ActionSupport;
import com.order.model.Customer;
import com.order.model.Shop;
import com.order.model.ShoppingCart;
import com.order.service.ShoppingCartService;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class ShoppingCartAction extends ActionSupport implements SessionAware{

    private ShoppingCart shoppingCart;
    private List<ShoppingCart> shoppingCarts;
    private Map<String,Object> session;
    @Autowired
    private ShoppingCartService cartService;
    public String add(){
        int customer_id = (int)session.get("customer_id");
        Customer customer = new Customer();
        customer.setId(customer_id);
        shoppingCart.setCustomer(customer);
        cartService.add(shoppingCart);
        return "json";
    }

    public String query(){
        int customer_id = (int)session.get("customer_id");
        shoppingCarts = cartService.shoppingCarts(customer_id);
        return SUCCESS;
    }

    public String remove(){
        cartService.delete(shoppingCart);
        return "json";
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
