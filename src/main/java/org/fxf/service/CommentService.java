package org.fxf.service;

import java.util.List;

import org.fxf.bean.Page;
import org.fxf.dto.CommentDto;
import org.fxf.dto.CommentForSubmitDto;
import org.fxf.dto.CommentListDto;

public interface CommentService {

	/**
     * 保存评论
     * @param commentForSubmitDto 提交的评论DTO对象
     * @return 是否保存成功：true-成功，false-失败
     */
    boolean add(CommentForSubmitDto commentForSubmitDto);
    
    /**
	 * 分页搜索评论列表
	 * @param ordersDto 查询条件(包含分页对象)
	 * @return 评论列表
	 */
	List<CommentDto> searchByPage(CommentDto commentDto);
    
    /**
     * 按分页条件，根据商户ID获取商户下的评论列表dto
     * @param businessId 商户ID
     * @param page 分页对象
     * @return 评论列表dto
     */
    CommentListDto getListByBusinessId(Long businessId,Page page);
}
