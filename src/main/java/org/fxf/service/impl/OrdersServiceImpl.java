package org.fxf.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.fxf.bean.Orders;
import org.fxf.constant.CommentStateConst;
import org.fxf.dao.OrdersDao;
import org.fxf.dto.OrdersDto;
import org.fxf.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService{
	
	@Autowired
	private OrdersDao ordersDao;
	
	@Value("${businessImage.url}")
	private String businessImageUrl;

	@Override
	public boolean add(OrdersDto ordersDto) {
		Orders orders=new Orders();
		BeanUtils.copyProperties(ordersDto, orders);
		orders.setCommentState(CommentStateConst.NOT_COMMENT);
		ordersDao.insert(orders);
		return true;
	}

	@Override
	public OrdersDto getById(Long id) {
		OrdersDto result=new OrdersDto();
		Orders orders=ordersDao.selectById(id);
		BeanUtils.copyProperties(orders, result);
		return result;
	}

	@Override
	public List<OrdersDto> getListByMemberId(Long memberId) {
		List<OrdersDto> result=new ArrayList<OrdersDto>();
		Orders ordersForSelect=new Orders();
		ordersForSelect.setMemberId(memberId);
		List<Orders> ordersList=ordersDao.select(ordersForSelect);
		for (Orders orders : ordersList) {
			OrdersDto ordersDto=new OrdersDto();
			result.add(ordersDto);
			BeanUtils.copyProperties(orders, ordersDto);
			ordersDto.setImg(businessImageUrl + orders.getBusiness().getImgFileName());
			ordersDto.setTitle(orders.getBusiness().getTitle());
			ordersDto.setCount(orders.getBusiness().getNumber());
		}
		return result;
	}

	@Override
	public List<OrdersDto> searchByPage(OrdersDto ordersDto) {
		List<OrdersDto> result = new ArrayList<OrdersDto>();
		Orders condition = new Orders();
		if (ordersDto.getTitle() != null && !ordersDto.getTitle().equals("")) {
			ordersDto.setId(Long.valueOf(ordersDto.getTitle()));
		}
		BeanUtils.copyProperties(ordersDto, condition);
		List<Orders> ordersList = ordersDao.selectByPage(condition);
		
		for (Orders orders : ordersList) {
			OrdersDto ordersDtoTemp = new OrdersDto();
			result.add(ordersDtoTemp);
			BeanUtils.copyProperties(orders, ordersDtoTemp);
		}
		
		return result;
	}

}
