package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.bean.Admins;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;
import cn.tedu.store.service.AdminsService;
import cn.tedu.store.service.IAdminsService;
import cn.tedu.store.service.IUserService;

@Controller
@RequestMapping("/user")
/**
 * 用于管理user与admin注册、登录等
 * @author LENOVO
 *
 */
public class UserController extends BaseController{
	@Resource
	private IUserService userService;
	
	@Resource
	private IAdminsService adminsService;
	
	//显示注册的视图
	@RequestMapping("/showRegister.do")
	public String showRegister() {
		return "register";
	}
	
	//显示登录页面
	@RequestMapping("/showLogin.do")
	public String showLogin() {
		return "login";
	}
	
	//显示管理员登录页面
	@RequestMapping("/showadminslogin.do")
	public String adminlogin() {
		return "adminslogin";
	}
	
	//退出
	@RequestMapping("/exit.do")
	public String exit(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	@RequestMapping("/adminexit.do")
	public String adminexit(HttpSession session) {
		session.invalidate();
		return "adminslogin";
	}
	
	//注册
	@RequestMapping("/register.do")
	@ResponseBody
	public User register(HttpServletRequest request){
		
		String username = request.getParameter("uname");
		String password = request.getParameter("upwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		System.out.println(phone);
		
		ResponseResult<Void> rr = null;
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		user.setCreatedTime(timestampDate());
		try {
			List<User> checkuser = userService.selectByPhone(phone);
			if(checkuser.size() == 0){
				userService.insertUser(user);
				return User.getJsonResult(200, "注册成功");
			}else{
				return User.getJsonResult(200, "注册失败");
			}
			
		}catch(RuntimeException ex) {
			return User.getJsonResult(200, "注册失败");
		}
	}
	
	//登录
	@RequestMapping("/login.do")
	@ResponseBody
	public User login(HttpServletRequest request, HttpSession session){
		ResponseResult<Void> rr = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User();
		user.setPassword(password);
		user.setPhone(username);
		
		try {
			List<User> checkUser = userService.login(user);
			if(checkUser.size() > 0){
				session.setAttribute("user", checkUser.get(0));
				return User.getJsonResult(200, "登录成功");
			}
			else {
				return User.getJsonResult(200, "登录失败");
			}
		} catch (RuntimeException ex) {
			return User.getJsonResult(200, "登录失败");
		}
	}
	
	//管理员登录
	@RequestMapping("/adminlogin.do")
	@ResponseBody
	public Admins adminlogin(HttpServletRequest request, HttpSession session){
		ResponseResult<Void> rr = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Admins admins = new Admins();
		admins.setApwd(password);
		admins.setAtel(username);
		
		try {
			List<Admins> checkUser = adminsService.login(admins);
			if(checkUser.size() > 0){
				session.setAttribute("admins", checkUser.get(0));
				return Admins.getJsonResult(200, "登录成功");
			}else {
				return Admins.getJsonResult(200, "登录失败");
			}
			
		} catch (RuntimeException ex) {
			return Admins.getJsonResult(200, "登录失败");
		}
	}
	
	
	//返回当前时间Timestamp格式数据
	public Timestamp timestampDate(){
		return strFormatTimetamp(returnDateTimeStr());
	}
	
	//根据当前时间返回时间戳
	public String shijianchuo(){
		return System.currentTimeMillis()+"";
	}
	
	public String returnDateTimeStr(){
		DateFormat df2 = DateFormat.getDateTimeInstance();
       return df2.format(new Date()); 
	}
	
	private Timestamp strFormatTimetamp(String str){
	    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    format.setLenient(false);
	    try {
		    Timestamp ts = new Timestamp(format.parse(str).getTime());
		    return ts;
	    } catch (Exception e) {
	     e.printStackTrace();
	     return null;
	    }
	}
	
	
}
















