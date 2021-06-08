package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.bean.News;
import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Notices;
import cn.tedu.store.bean.PageBean;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.bean.User;
import cn.tedu.store.service.INewsTypesService;
import cn.tedu.store.service.INoticesService;
import cn.tedu.store.service.IUserService;

@Controller
@RequestMapping("/notices")
public class NoticesController extends BaseController{
	@Resource
	private INoticesService noticesService;
	
	//新闻列表类型管理
	@RequestMapping("/noticesmana.do")
	public String showIndex(PageBean page, ModelMap map,HttpServletRequest request,HttpSession session) {
		
		if(session.getAttribute("admins") != null && !session.getAttribute("admins").toString().equals("")){
			List<Notices> list = noticesService.selectAllNotices();
			SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd"); 
			for(int i = 0; i < list.size(); i++)
				list.get(i).setFormattime(dateformat2.format(list.get(i).getNtime()));
			
			pagingindex(page,list,request);
			
			map.addAttribute("list", page.getDataList());
			return "noticesmana";			
		}else{
			return "adminslogin";
		}
		
	}
	
	@RequestMapping("/goNoticesDetail.do")
	public String gousersNewsDetail(PageBean page, ModelMap map,HttpServletRequest request,HttpSession session) {
		int nid = Integer.parseInt(request.getParameter("nid").trim());
		
		Notices noticesdetails = noticesService.selectNoticesById(nid);
		SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd"); 
		noticesdetails.setFormattime(dateformat2.format(noticesdetails.getNtime()));
		
		request.setAttribute("noticesdetails", noticesdetails);
		
		return "noticesDetail";
	}
	
	//插入一个新的通知
	@RequestMapping("/insetnotices.do")
	@ResponseBody
	public ResponseResult<Void> insetnotices(HttpServletRequest request) throws Exception{
		
		String name = request.getParameter("name").trim();
		name = URLDecoder.decode(name,"UTF-8");
		
		String content = request.getParameter("content").trim();
		content = URLDecoder.decode(content,"UTF-8");
		
		ResponseResult<Void> rr = null;
		Notices notices = new Notices();
		notices.setNcont(content);
		notices.setNtime(timestampDate());
		notices.setNtitle(name);
		try {
			List<Notices> checkNts = noticesService.checkNoticesByname(name);
			if(checkNts.size() == 0){
				noticesService.insertNotices(notices);
				
				rr = new ResponseResult<Void>(1, "添加数据成功");
			}else{
				rr = new ResponseResult<Void>(1, "添加数据失败");
			}
			
		}catch(RuntimeException ex) {
			rr = new ResponseResult<Void>(0, ex.getMessage());
		}
		return rr;
	}
	
	//更新通知
	@RequestMapping("/updatenotices.do")
	@ResponseBody
	public ResponseResult<Void> updatenotices(HttpServletRequest request) throws Exception{
		
		String name = request.getParameter("name").trim();
		name = URLDecoder.decode(name,"UTF-8");
		
		String content = request.getParameter("content").trim();
		content = URLDecoder.decode(content,"UTF-8");
		
		int tid = Integer.parseInt(request.getParameter("tid").trim());
		
		ResponseResult<Void> rr = null;
		Notices notices = new Notices();
		notices.setNcont(content);
		notices.setNtitle(name);
		notices.setNid(tid);
		
		try {
			List<Notices> checkNts = noticesService.checkNoticesByname(name);
			if(checkNts.size() == 0 || checkNts.size() == 1){
				noticesService.updateNotices(notices);
				
				rr = new ResponseResult<Void>(1, "修改数据成功");
			}else{
				rr = new ResponseResult<Void>(1, "修改数据失败");
			}
			
		}catch(RuntimeException ex) {
			rr = new ResponseResult<Void>(0, ex.getMessage());
		}
		return rr;
	}
	
	
	@RequestMapping("/getNoticesById.do")
	@ResponseBody
	public ResponseResult<Notices> getNoticesById(HttpServletRequest request) throws Exception{
		
		int tid = Integer.parseInt(request.getParameter("tid").trim());
		
		ResponseResult<Notices> rr = null;

		try {
			Notices notice = noticesService.selectNoticesById(tid);
			if(notice != null){
				rr = new ResponseResult<>(1,"请求数据成功", notice);
			}else{
				rr = new ResponseResult<>(1, "添加数据失败");
			}
			
		}catch(RuntimeException ex) {
			rr = new ResponseResult<>(0, ex.getMessage());
		}
		return rr;
	}
	
	//删除一个通知
	@RequestMapping("/deleteNoticeById.do")
	@ResponseBody
	public ResponseResult<Void> deleteNoticeById(HttpServletRequest request, HttpSession session){
		ResponseResult<Void> rr = null;
		int tid = Integer.parseInt(request.getParameter("id"));
		noticesService.deleteNoticesById(tid);
		rr = new ResponseResult<>(1, "删除成功");
		return rr;
	}
	
	
	public <T> void pagingindex(PageBean page,List<T> list,HttpServletRequest request){
		if (page.getCurrentPage() == null){
	        page.setCurrentPage(1);
	    } else {
	        page.setCurrentPage(page.getCurrentPage());
	    }
	    
	    page.setPageSize(8);
	    page.setStar((page.getCurrentPage() - 1) * page.getPageSize());
	    int count = list.size();
	    page.setTotalPage(count % 8 == 0 ? count / 8 : count / 8 + 1);
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
	
}
















