package cn.tedu.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.tedu.store.bean.User;

public class LoginInterceptor implements HandlerInterceptor{
	//��ִ�п���������֮ǰ 
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1.��ȡ��session���󣬴�session������ȡ��user
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null) {
			//2.userΪ�ձ�ʾû��¼������ת����¼ҳ�棨�ض��򣩣�����false
			String path = request.getContextPath()+"/user/showLogin.do";
			response.sendRedirect(path);
			return false;
		}else {
			//3.user��Ϊ�գ�����true������
			return true;
		}
		
		
		
	}
	//����������ִ��֮�󣬵�����Ӧҳ��֮ǰ
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	//��Ӧҳ��֮��
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}




















