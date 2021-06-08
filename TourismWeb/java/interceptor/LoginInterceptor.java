package cn.tedu.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.tedu.store.bean.User;

public class LoginInterceptor implements HandlerInterceptor{
	//在执行控制器方法之前 
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1.获取到session对象，从session对象中取出user
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null) {
			//2.user为空表示没登录过，跳转到登录页面（重定向），返回false
			String path = request.getContextPath()+"/user/showLogin.do";
			response.sendRedirect(path);
			return false;
		}else {
			//3.user不为空，返回true，放行
			return true;
		}
		
		
		
	}
	//控制器方法执行之后，但是响应页面之前
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	//响应页面之后
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}




















