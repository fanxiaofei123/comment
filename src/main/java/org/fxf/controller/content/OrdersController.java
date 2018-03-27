package org.fxf.controller.content;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.fxf.dto.OrdersDto;
import org.fxf.service.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrdersController {
	
	@Resource
	private OrdersService ordersService;

	@RequestMapping
	public String init(Model model,HttpServletRequest request){
		OrdersDto ordersDto = new OrdersDto();
		model.addAttribute("list", ordersService.searchByPage(ordersDto));
		model.addAttribute("searchParam", ordersDto);
		return "content/orderList";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping("/search")
	public String search(Model model,OrdersDto ordersDto){
		model.addAttribute("list", ordersService.searchByPage(ordersDto));
		model.addAttribute("searchParam", ordersDto);
		return "content/orderList";
	}
}
