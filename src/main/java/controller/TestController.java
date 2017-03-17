package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test", produces = { "application/json; charset=UTF-8" })
public class TestController {
	@RequestMapping(value="/testConnection")
	@ResponseBody
	public String testConnection(){
		return "hello world springboot";
	}
}
