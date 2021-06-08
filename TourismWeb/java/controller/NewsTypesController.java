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

import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.PageBean;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.bean.User;
import cn.tedu.store.service.INewsTypesService;
import cn.tedu.store.service.IUserService;

@Controller
@RequestMapping("/newstypes")
public class NewsTypesController extends BaseController{
	@Resource
	private INewsTypesService newsTypesService;
	
	//新闻列表类型管理
	@RequestMapping("/newstypesmana.do")
	public String showIndex(PageBean page, ModelMap map,HttpServletRequest request,HttpSession session) {
		
		if(session.getAttribute("admins") != null && !session.getAttribute("admins").toString().equals("")){
			List<NewsTypes> list = newsTypesService.selectAllNewsTypes();
			SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd"); 
			for(int i = 0; i < list.size(); i++)
				list.get(i).setFormattime(dateformat2.format(list.get(i).getTtime()));
			
			pagingindex(page,list,request);
			
			map.addAttribute("list", page.getDataList());
			return "newstypesmana";			
		}else{
			return "adminslogin";
		}
		
	}
	
	//插入新的新闻类型
	@RequestMapping("/insetnewstypes.do")
	@ResponseBody
	public ResponseResult<Void> insetnewstypes(HttpServletRequest request) throws Exception{
		
		String ntype = request.getParameter("name").trim();
		ntype = URLDecoder.decode(ntype,"UTF-8");
		
		ResponseResult<Void> rr = null;
		NewsTypes newsTypes = new NewsTypes();
		newsTypes.setTname(ntype);
		newsTypes.setTtime(timestampDate());
		try {
			List<NewsTypes> checkNts = newsTypesService.checkNewsTypesByname(ntype);
			if(checkNts.size() == 0){
				newsTypesService.insertNewsTypes(newsTypes);
				rr = new ResponseResult<Void>(1, "添加数据成功");
			}else{
				rr = new ResponseResult<Void>(1, "添加数据失败");
			}
			
		}catch(RuntimeException ex) {
			rr = new ResponseResult<Void>(0, ex.getMessage());
		}
		return rr;
	}
	//
	@RequestMapping("/updatenewstypes.do")
	@ResponseBody
	public ResponseResult<Void> updatenewstypes(HttpServletRequest request) throws Exception{
		
		String ntype = request.getParameter("name").trim();
		ntype = URLDecoder.decode(ntype,"UTF-8");
		
		int tid = Integer.parseInt(request.getParameter("tid").trim());
		
		ResponseResult<Void> rr = null;
		NewsTypes newsTypes = new NewsTypes();
		newsTypes.setTname(ntype);
		newsTypes.setTid(tid);
		
		try {
			List<NewsTypes> checkNts = newsTypesService.checkNewsTypesByname(ntype);
			if(checkNts.size() == 0 || checkNts.size() == 1){
				newsTypesService.updateNewsTypes(newsTypes);
				
				rr = new ResponseResult<Void>(1, "添加数据成功");
			}else{
				rr = new ResponseResult<Void>(1, "添加数据失败");
			}
			
		}catch(RuntimeException ex) {
			rr = new ResponseResult<Void>(0, ex.getMessage());
		}
		return rr;
	}
	
	//根据id查找newsTypes
	@RequestMapping("/getNewstypesById.do")
	@ResponseBody
	public ResponseResult<NewsTypes> getNewstypesById(HttpServletRequest request) throws Exception{
		
		int tid = Integer.parseInt(request.getParameter("tid").trim());
		
		ResponseResult<NewsTypes> rr = null;

		try {
			NewsTypes newsTypes = newsTypesService.selectNewsTypesById(tid);
			if(newsTypes != null){
				rr = new ResponseResult<>(1,"请求数据成功", newsTypes);
			}else{
				rr = new ResponseResult<>(1, "添加数据失败");
			}
			
		}catch(RuntimeException ex) {
			rr = new ResponseResult<>(0, ex.getMessage());
		}
		return rr;
	}
	//删除对应的新闻类型
	@RequestMapping("/deleteNTypeById.do")
	@ResponseBody
	public ResponseResult<Void> deleteNTypeById(HttpServletRequest request, HttpSession session){
		ResponseResult<Void> rr = null;
		int tid = Integer.parseInt(request.getParameter("id"));
		newsTypesService.deleteNewsTypesById(tid);
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
















