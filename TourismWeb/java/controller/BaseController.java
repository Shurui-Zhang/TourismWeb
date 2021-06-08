package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import cn.tedu.store.bean.User;

public class BaseController {
	//获取用户id的值
	public Integer getId(HttpSession session) {
		Integer id = null;
		User user = (User)session.getAttribute("user");
		if(user != null) {
			id = user.getId();
		}
		return id;
	}
}


















