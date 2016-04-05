package com.tarena.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.common.Md5Util;
import com.tarena.entity.Result;
import com.tarena.entity.User;
import com.tarena.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	/**
	 * 注册用户
	 */
	@RequestMapping("/register.do")
	@ResponseBody
	public Result register(User user) {
		boolean b = userService.createUser(user);
		return new Result(b);
	}

	@RequestMapping("/login.do")
	@ResponseBody
	public Result login(String userName, 
			String password, HttpSession session) {
		Map<String, Object> data = 
			userService.checkUser(userName, password);
		if ("0".equals(data.get("flag").toString())) {
			//登录成功
			User user = userService.findUser(userName);
			data.put("user", user);
			session.setAttribute("user", user);
		}

		return new Result(data);
	}
	
	@RequestMapping("/logout.do")
	@ResponseBody
	public Result logout(HttpSession session) {
		//注销session
		session.invalidate();
		return new Result();
	}
	
	@RequestMapping("/changePassword.do")
	@ResponseBody
	public Result changePassword(String lastPassword, 
			String newPassword, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user.getCn_user_password()
				.equals(Md5Util.md5(lastPassword))) {
			user.setCn_user_password(Md5Util.md5(newPassword));
			userService.update(user);
			return new Result("修改成功.");
		} else {
			return new Result("原密码输入有误.");
		}
	}

}
