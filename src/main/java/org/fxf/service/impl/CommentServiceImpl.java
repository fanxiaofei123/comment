package org.fxf.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fxf.bean.Business;
import org.fxf.bean.Comment;
import org.fxf.bean.Orders;
import org.fxf.bean.Page;
import org.fxf.constant.CommentStateConst;
import org.fxf.dao.CommentDao;
import org.fxf.dao.OrdersDao;
import org.fxf.dto.CommentDto;
import org.fxf.dto.CommentForSubmitDto;
import org.fxf.dto.CommentListDto;
import org.fxf.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private OrdersDao ordersDao;

	@Override
	@Transactional
	public boolean add(CommentForSubmitDto commentForSubmitDto) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentForSubmitDto, comment);
		comment.setId(null);
		comment.setOrdersId(commentForSubmitDto.getId());
		comment.setCreateTime(new Date());
		// 保存评论
		commentDao.insert(comment);
		
		Orders orders = new Orders();
		orders.setId(commentForSubmitDto.getId());
		orders.setCommentState(CommentStateConst.HAS_COMMENT);
		// 更新订单评论状态
		ordersDao.update(orders);
		return true;
	}

	@Override
	public CommentListDto getListByBusinessId(Long businessId, Page page) {
		CommentListDto result= new CommentListDto();
		// 组织查询条件
		Comment comment=new Comment();
		Orders orders=new Orders();
		Business business=new Business();
		// 评论里包含了订单对象
		comment.setOrders(orders);
		// 订单对象里包含了商户对象
		orders.setBusiness(business);
		// 设置商户主键
		business.setId(businessId);
		// 前端app页码从0开始计算，这里需要+1
		page.setCurrentPage(page.getCurrentPage()+1);
		// 设置分页条件
		comment.setPage(page);
		List<Comment> commentList = commentDao.selectByPage(comment);
		
		// 组织返回值
		List<CommentDto> data=new ArrayList<>();
		result.setData(data);
		for (Comment commentTemp : commentList) {
			CommentDto commentDto=new CommentDto();
			data.add(commentDto);
			BeanUtils.copyProperties(commentTemp, commentDto);
			// 隐藏手机号中间4位
			StringBuffer phoneBuffer=new StringBuffer(String.valueOf(commentTemp.getOrders().getMember().getPhone()));
			commentDto.setUsername(phoneBuffer.replace(3, 7, "****").toString());
		}
		result.setHasMore(page.getCurrentPage() < page.getTotalPage());
		return result;
	}

	@Override
	public List<CommentDto> searchByPage(CommentDto commentDto) {
		List<CommentDto> result = new ArrayList<CommentDto>();
		Comment condition = new Comment();
		if (commentDto.getTitle() != null && !commentDto.getTitle().equals("")) {
			commentDto.setComment(commentDto.getTitle());
		}
		
		BeanUtils.copyProperties(commentDto, condition);
		List<Comment> commentList = commentDao.selectByPage(condition);
		
		for (Comment comment : commentList) {
			CommentDto commentDtoTemp = new CommentDto();
			result.add(commentDtoTemp);
			BeanUtils.copyProperties(comment, commentDtoTemp);
		}
		return result;
	}

}
