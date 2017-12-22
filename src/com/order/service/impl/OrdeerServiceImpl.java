package com.order.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.dao.OrdeerDao;
import com.order.model.Ordeer;
import com.order.service.OrdeerService;
import org.springframework.transaction.annotation.Transactional;

@Service("ordeerService")
@Transactional
public class OrdeerServiceImpl implements OrdeerService{
	@Autowired
	private OrdeerDao ordeerDao;
	
	@Override
	public int add(Ordeer ordeer) {
		ordeer.setOrderTime(new Date());
		int status = ordeerDao.add(ordeer);
		return status;
	}

	@Override
	public int delete(Ordeer ordeer) {
		int status = ordeerDao.deleteById(ordeer.getId());
		return status;
	}

	@Override
	public int updateStatus(Ordeer ordeer,int status) {

		int code = ordeerDao.updateStatus(ordeer.getId(), status);
		if(status==99){
			System.out.println(99);
			try {
				int c = ordeerDao.updateArriveTime(ordeer.getId());
				System.out.println(c);
			}catch (Exception e){
				e.printStackTrace();
			}

		}

		return code;
	}
	
}
