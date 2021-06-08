package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.bean.Admins;
import cn.tedu.store.bean.News;
import cn.tedu.store.bean.User;
import cn.tedu.store.bean.Notices;
import cn.tedu.store.bean.PageBean;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;
import cn.tedu.store.service.INewsService;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.INoticesService;
import cn.tedu.store.service.IScenerysService;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.NewsService;
import cn.tedu.store.service.ScenerysService;

@Controller
@RequestMapping("/users")
/**
 * 用于管理全部的user，增删改查
 * @author LENOVO
 *
 */
public class UsersManaController extends BaseController{
	@Resource
	private IUserService usersService;
	
	@RequestMapping("/usersoneselfmana.do")
	public String usersoneselfmana(PageBean page, ModelMap map,HttpServletRequest request,HttpSession session) {
		
		if(session.getAttribute("user") != null && !session.getAttribute("user").toString().equals("")){
			
			User user = (User)session.getAttribute("user");
			User users = usersService.selectUserById(user.getId());
			
			map.addAttribute("usersdetailsss", users);
			
			return "usersoneselfmana";		
		}else{
			return "login";
		}
	}
	//更新user
	@RequestMapping("/updateUsers.do")
	@ResponseBody
	public ResponseResult<Void> updateAdmins(HttpServletRequest request) throws Exception{
		
		String name = request.getParameter("name").trim();
		name = URLDecoder.decode(name,"UTF-8");
		
		String pwd = request.getParameter("pwd").trim();
		
		String tel = request.getParameter("tel").trim();
		
		String email = request.getParameter("email").trim();
		
		String img = request.getParameter("img").trim();
		
		int tid = Integer.parseInt(request.getParameter("tid").trim());
		
		ResponseResult<Void> rr = null;
		
		User users = new User();
		users.setEmail(email);
		users.setImage(img);
		users.setPassword(pwd);
		users.setPhone(tel);
		users.setUsername(name);
		users.setId(tid);
		
		try {
			List<User> checkNts = usersService.selectByPhone(tel);
			if(checkNts.size() == 0 || checkNts.size() == 1){
				usersService.updateUser(users);
				
				rr = new ResponseResult<Void>(1, "修改数据成功");
			}else{
				rr = new ResponseResult<Void>(1, "修改数据失败");
			}
			
		}catch(RuntimeException ex) {
			rr = new ResponseResult<Void>(0, ex.getMessage());
		}
		return rr;
	}
	
	@RequestMapping("/getUsersById.do")
	@ResponseBody
	public ResponseResult<User> getUsersById(HttpServletRequest request) throws Exception{
		
		int tid = Integer.parseInt(request.getParameter("tid").trim());
		
		ResponseResult<User> rr = null;

		try {
			User users = usersService.selectUserById(tid);
			if(users != null){
				rr = new ResponseResult<>(1,"请求数据成功", users);
			}else{
				rr = new ResponseResult<>(1, "添加数据失败");
			}
			
		}catch(RuntimeException ex) {
			rr = new ResponseResult<>(0, ex.getMessage());
		}
		return rr;
	}
	
	//显示用户管理列表
	@RequestMapping("/usersmana.do")
	public String showIndex(PageBean page, ModelMap map,HttpServletRequest request,HttpSession session) {
		
		if(session.getAttribute("admins") != null && !session.getAttribute("admins").toString().equals("")){
			List<User> list = usersService.selectAllUsers();
			SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd"); 
			for(int i = 0; i < list.size(); i++)
				list.get(i).setFormattime(dateformat2.format(list.get(i).getCreatedTime()));
			
			if (page.getCurrentPage() == null){
		        page.setCurrentPage(1);
		    } else {
		        page.setCurrentPage(page.getCurrentPage());
		    }
		    
		    page.setPageSize(5);
		    page.setStar((page.getCurrentPage() - 1) * page.getPageSize());
		    int count = list.size();
		    page.setTotalPage(count % 5 == 0 ? count / 5 : count / 5 + 1);
		    page.setDataList(list.subList(page.getStar(),count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
		    request.setAttribute("paging", page);
			
			map.addAttribute("userslist", page.getDataList());
			return "usersmana";			
		}else{
			return "adminslogin";
		}
		
	}
	
	//管理员删除游客
	@RequestMapping("/deleteUsersById.do")
	@ResponseBody
	public ResponseResult<Void> deleteUsersById(HttpServletRequest request, HttpSession session){
		ResponseResult<Void> rr = null;
		int tid = Integer.parseInt(request.getParameter("id"));
		usersService.deleteUsersById(tid);
		rr = new ResponseResult<>(1, "删除成功");
		return rr;
	}
	
	public <T> void pagingindex(PageBean page,List<T> list,HttpServletRequest request){
		if (page.getCurrentPage() == null){
	        page.setCurrentPage(1);
	    } else {
	        page.setCurrentPage(page.getCurrentPage());
	    }
	    
	    page.setPageSize(5);
	    page.setStar((page.getCurrentPage() - 1) * page.getPageSize());
	    int count = list.size();
	    page.setTotalPage(count % 5 == 0 ? count / 5 : count / 5 + 1);
	    page.setDataList(list.subList(page.getStar(),count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
	    request.setAttribute("paging", page);  
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
	
	//上传图片的方法
	@RequestMapping("/uplodephoto.do")
    @ResponseBody
    public  Map<String, Object>  updatePhoto(HttpServletRequest request,HttpServletResponse response,@RequestParam("myFile") MultipartFile myFile){
		 Map<String, Object> json = new HashMap<String, Object>();
	     System.out.println("fsdfsdfds");   
		 try {
	            //输出文件后缀名称
	            System.out.println(myFile.getOriginalFilename());
	            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	            //图片名称
	            String name = df.format(new Date());

	            Random r = new Random();
	            for(int i = 0 ;i<3 ;i++){
	                name += r.nextInt(10);
	            }
	            
	            String ext = FilenameUtils.getExtension(myFile.getOriginalFilename());
	            //保存图片       File位置 （全路径）   /upload/fileName.jpg
	            String url = request.getSession().getServletContext().getRealPath("/upload");
	            //相对路径
	            String path = "/"+name + "." + ext;
	            File file = new File(url);
	            if(!file.exists()){
	                file.mkdirs();
	            }
	            
	            myFile.transferTo(new File(url+path));
	            /*json.put("success", "/static/img/upload/phono/"+path);*/
	            json.put("success",path);
	            
	            System.out.println("url="+url);
	            System.out.println("path="+path);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	       return json ;
    }
	
	
}
















