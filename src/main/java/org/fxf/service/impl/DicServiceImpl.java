package org.fxf.service.impl;

import java.util.List;

import org.fxf.bean.Dic;
import org.fxf.dao.DicDao;
import org.fxf.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DicServiceImpl implements DicService{
	
	@Autowired
	private DicDao dicDao;

	@Override
	public List<Dic> getListByType(String type) {
		Dic dic =new Dic();
		dic.setType(type);
		return dicDao.select(dic);
	}

}
