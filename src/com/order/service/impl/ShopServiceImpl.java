package com.order.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.dao.ShopDao;
import com.order.model.Food;
import com.order.model.Ordeer;
import com.order.model.Shop;
import com.order.service.ShopService;

@Service("shopService")
@Transactional
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopDao shopDao;
	
	@Override
	@Transactional(readOnly=true)
	public Shop getByPhoneNum(Shop shop) {
		Shop s = shopDao.getByPhoneNum(shop.getPhoneNum());
		return s;
	}

	@Override
	@Transactional(readOnly=true)
	public Shop get(Shop shop) {
		Shop s = shopDao.getById(shop.getId());
		return s;
	}

	@Override
	@Transactional(readOnly=true)
	public Set<Food> getFoods(Shop shop) {
		Set<Food> foods = shopDao.getFoodsById(shop.getId());
		return foods;
	}

	@Override
	@Transactional(readOnly=true)
	public Set<Ordeer> getOrdeers(Shop shop) {
		Set<Ordeer> ordeers = shopDao.getOrdeersById(shop.getId());
		return ordeers;
	}

	

	@Override
	public Set<Ordeer> getOrdeers(Shop shop, int status,int type) {
		if(status==-2){
			return getOrdeers(shop);
		}else{
			Set<Ordeer> ordeers = shopDao.getOrdeers(shop.getId(), status,type);
			return ordeers;
		}
	}

	@Override
	public Set<Ordeer> getTurnover(Shop shop,int recent_day) {
		return shopDao.getTurnover(shop.getId(),recent_day);
	}

	@Override
	public int register(Shop shop) {
		//手机号码是否已经被注册了
		if(shopDao.getByPhoneNum(shop.getPhoneNum())!=null)
			return 0;
		
		return shopDao.add(shop);
	}

	@Override
	public int update(Shop shop) {
		return shopDao.update(shop);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Shop> getAll(int status) {
		return shopDao.getAll(status);
	}

	@Override
	public int modifyPsw(Shop shop) {
		//获取用户旧密码
		String oldPsw = shopDao.getById(shop.getId()).getPassword();
		String psws[] = shop.getPassword().split(",");
		if(!oldPsw.equals(psws[0]))
			return 0;
		int code = shopDao.updatePswById(shop.getId(), psws[1]);
		return code;
	}

	@Override
	public int updateStatus(Shop shop, int status) {
		return shopDao.updateStatusById(shop.getId(), status);
	}

	public List<Shop> getByKey(String message){
		return shopDao.getByKey(message);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Shop> getOpenShops() {
		return shopDao.getOpenshops();
	}

	@Override
	public int updateOpen(int shop_id, int open) {
		return shopDao.updateOpen(shop_id,open);
	}
}
