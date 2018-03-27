package org.fxf.controller.content;

import javax.annotation.Resource;

import org.fxf.dto.CommentDto;
import org.fxf.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
public class CommentsController {
	
	@Resource
	private CommentService commentService;

	@RequestMapping
	public String init(Model model) {
		CommentDto commentDto = new CommentDto();
		model.addAttribute("list", commentService.searchByPage(commentDto));
		model.addAttribute("searchParam", commentDto);
		return "content/commentList";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping("/search")
	public String search(Model model,CommentDto commentDto){
		model.addAttribute("list", commentService.searchByPage(commentDto));
		model.addAttribute("searchParam", commentDto);
		return "content/commentList";
	}
}
