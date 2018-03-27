package org.fxf.service.impl;

import java.util.List;

import org.fxf.bean.User;
import org.fxf.dao.UserDao;
import org.fxf.dto.UserDto;
import org.fxf.service.UserService;
import org.fxf.util.CommonUtil;
import org.fxf.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public boolean validate(UserDto userDto) {
		if (userDto != null && !CommonUtil.isEmpty(userDto.getName()) && !CommonUtil.isEmpty(userDto.getPassword())) {
			User user = new User();
			BeanUtils.copyProperties(userDto, user);
			List<User> list=userDao.select(user);
			if (list.size() == 1) {
				BeanUtils.copyProperties(list.get(0), userDto);
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public List<UserDto> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modify(UserDto userDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(UserDto userDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Long id) {
		return userDao.delete(id) == 1;
	}

	@Override
	public UserDto getById(Long id) {
		UserDto userDto=new UserDto();
		User user=userDao.selectById(id);
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}

}
