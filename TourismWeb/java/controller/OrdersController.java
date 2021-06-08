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
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.Notices;
import cn.tedu.store.bean.Orders;
import cn.tedu.store.bean.PageBean;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;
import cn.tedu.store.service.INoticesService;
import cn.tedu.store.service.IOrdersService;
import cn.tedu.store.service.IScenerysService;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.OrdersService;
import cn.tedu.store.service.ScenerysService;

@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseController{
	@Resource
	private IOrdersService ordersService;
	
	//显示当前游客的订单
	@RequestMapping("/usersOrdersmana.do")
	public String usersOrdersmana(PageBean page, ModelMap map,HttpServletRequest request,HttpSession session) {
		
		if(session.getAttribute("user") != null && !session.getAttribute("user").toString().equals("")){
			User user = (User) session.getAttribute("user");
			
			List<Orders> list = ordersService.selectOrdersById(user.getId());
			SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd"); 
			for(int i = 0; i < list.size(); i++){
				list.get(i).setCreateformattime(dateformat2.format(list.get(i).getOcreatetime()));
				list.get(i).setOrderformattime((dateformat2.format(list.get(i).getOcreatetime())));
			}
			
			pagingindex(page,list,request);
			
			map.addAttribute("userorderslist", page.getDataList());
			
			return "usersAllOrders";			
			
		}else{
			return "redirect:/user/showLogin.do";
		}
	}
	
	//调出管理景点页面
	@RequestMapping("/ordersmana.do")
	public String showIndex(PageBean page, ModelMap map,HttpServletRequest request,HttpSession session) {
		
		if(session.getAttribute("admins") != null && !session.getAttribute("admins").toString().equals("")){
			List<Orders> list = ordersService.selectAllOrders();
			SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd"); 
			for(int i = 0; i < list.size(); i++){
				list.get(i).setCreateformattime(dateformat2.format(list.get(i).getOcreatetime()));
				list.get(i).setOrderformattime((dateformat2.format(list.get(i).getOcreatetime())));
			}
			
			pagingindex(page,list,request);
			
			map.addAttribute("orderslist", page.getDataList());
			
			return "ordersmana";			
		}else{
			return "adminslogin";
		}
	}
	
	//管理员删除订单
	@RequestMapping("/deleteOrdersById.do")
	@ResponseBody
	public ResponseResult<Void> deleteOrdersById(HttpServletRequest request, HttpSession session){
		ResponseResult<Void> rr = null;
		int tid = Integer.parseInt(request.getParameter("id"));
		ordersService.deleteOrdersById(tid);
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
















