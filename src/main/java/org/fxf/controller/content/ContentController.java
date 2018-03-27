package org.fxf.controller.content;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/content")
public class ContentController {

	@RequestMapping("/index")
	public String content(){
		return "system/index";
	}
}
