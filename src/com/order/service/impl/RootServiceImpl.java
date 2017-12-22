package com.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.dao.RootDao;
import com.order.model.Root;
import com.order.service.RootService;

@Service("rootService")
@Transactional
public class RootServiceImpl implements RootService{
	@Autowired
	private RootDao rootDao;


	@Override
	public int modifyPsw(Root root) {
		String psws[] = root.getPassword().split(",");
		String oldPsw = rootDao.getPswById(root.getId());
		if(oldPsw.equals(psws[0])){
			return rootDao.updatePsw(psws[1],root.getId());
		}
		return -1;
	}
	
	
}
