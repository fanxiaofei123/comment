package org.fxf.controller.system;

import javax.servlet.http.HttpSession;

import org.fxf.constant.PageCodeEnum;
import org.fxf.constant.SessionKeyConst;
import org.fxf.dto.UserDto;
import org.fxf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;

	/**
	 * 登录页面
	 */
	@RequestMapping
	public String index(){
		return "system/login";
	}
	
	/**
	 * session超时
	 */
	@RequestMapping("/sessionTimeout")
	public String sessionTimeout(Model model){
		model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.SESSION_TIMEOUT);
		return "system/error";
	}
	
	/**
	 * 验证用户名/密码是否正确 验证通过跳转至后台管理首页，验证失败，返回至登录页。
	 */
	@RequestMapping(value="/validate",method=RequestMethod.POST)
	public String validate(UserDto userDto,RedirectAttributes attr){
		if (userService.validate(userDto)) {
			session.setAttribute(SessionKeyConst.USER_INFO, userDto);
			return "system/index";
		}
		attr.addFlashAttribute(PageCodeEnum.KEY, PageCodeEnum.LOGIN_FAIL);
		return "redirect:/login";
	}
}
