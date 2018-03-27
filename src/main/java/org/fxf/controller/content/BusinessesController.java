package org.fxf.controller.content;

import javax.annotation.Resource;

import org.fxf.constant.DicTypeConst;
import org.fxf.constant.PageCodeEnum;
import org.fxf.dto.BusinessDto;
import org.fxf.service.BusinessService;
import org.fxf.service.DicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/businesses")
public class BusinessesController {
	
	@Resource
	private DicService dicService;
	
	@Resource
	private BusinessService businessService;
	
	/**
	 * 商户列表初始化
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String init(Model model,BusinessDto dto){
		BusinessDto businessDto = new BusinessDto();
		model.addAttribute("list", businessService.searchByPage(businessDto));
		model.addAttribute("searchParam", businessDto);
		return "content/businessList";
	}
	
	/**
	 * 商户列表
	 */
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(Model model,BusinessDto dto){
		model.addAttribute("list", businessService.searchByPage(dto));
		model.addAttribute("searchParam", dto);
		return "content/businessList";
	}
	
	/**
	 * 删除商户
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public String remove(@PathVariable("id") Long id,Model model){
		if (businessService.remove(id)) {
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_SUCCESS);
		}else {
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_FAIL);
		}
		return "redirect:/businesses";
	}
	
	/**
	 * 商户新增页初始化
	 */
	@RequestMapping(value="/addPage",method=RequestMethod.GET)
	public String addInit(Model model){
		model.addAttribute("cityList", dicService.getListByType(DicTypeConst.CITY));
		model.addAttribute("categoryList", dicService.getListByType(DicTypeConst.CATEGORY));
		return "content/businessAdd";
	}
	
	/**
	 * 商户新增
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String add(BusinessDto dto,RedirectAttributes attr){
		if (businessService.add(dto)) {
			attr.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
			return "redirect:/businesses";
		}else {
			attr.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
			return "redirect:/businesses/addPage";
		}
	}
	
	/**
	 * 商户修改页初始化
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String modifyInit(Model model,@PathVariable("id") Long id){
		model.addAttribute("cityList", dicService.getListByType(DicTypeConst.CITY));
		model.addAttribute("categoryList", dicService.getListByType(DicTypeConst.CATEGORY));
		model.addAttribute("modifyObj", businessService.getById(id));
		return "content/businessModify";
	}
	
	/**
	 * 商户修改
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String modify(@PathVariable("id") Long id,BusinessDto dto,Model model){
		model.addAttribute("cityList", dicService.getListByType(DicTypeConst.CITY));
		model.addAttribute("categoryList", dicService.getListByType(DicTypeConst.CATEGORY));
		model.addAttribute("modifyObj", dto);
		if (businessService.modify(dto)) {
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_SUCCESS);
		}else {
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_FAIL);
		}
		return "redirect:/businesses";
	}

}
