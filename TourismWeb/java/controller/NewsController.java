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
import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Notices;
import cn.tedu.store.bean.PageBean;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;
import cn.tedu.store.service.INewsService;
import cn.tedu.store.service.INewsTypesService;
import cn.tedu.store.service.INoticesService;
import cn.tedu.store.service.IScenerysService;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.NewsService;
import cn.tedu.store.service.ScenerysService;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController{
	@Resource
	private INewsService newsService;
	@Resource
	private INewsTypesService newstypeservice;

	
	//进入新闻详情
	@RequestMapping("/gousersNewsDetail.do")
	public String gousersNewsDetail(PageBean page, ModelMap map,HttpServletRequest request,HttpSession session) {
		int nid = Integer.parseInt(request.getParameter("nid").trim());
		
		News newsdetails = newsService.selectNewsById(nid);
		SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd"); 
		newsdetails.setFormattime(dateformat2.format(newsdetails.getNtime()));
		
		request.setAttribute("newsdetails", newsdetails);
		
		return "usersNewsDetail";
	}
	
	//用户查看所有新闻列表
	@RequestMapping("/usersAllnewss.do")
	public String usersAllnewss(PageBean page, ModelMap map,HttpServletRequest request,HttpSession session) {
		
		List<News> list = newsService.selectAllNews();
		SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd"); 
		for(int i = 0; i < list.size(); i++)
			list.get(i).setFormattime(dateformat2.format(list.get(i).getNtime()));
		
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
	    request.setAttribute("newspaging", page);
		map.addAttribute("newslist", page.getDataList());
		return "usersAllNewss";
	}
	
	
	//管理员新闻管理
	@RequestMapping("/newssmana.do")
	public String showIndex(PageBean page, ModelMap map,HttpServletRequest request,HttpSession session) {
		
		if(session.getAttribute("admins") != null && !session.getAttribute("admins").toString().equals("")){
			
			List<News> list = newsService.selectAllNews();
			SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd"); 
			for(int i = 0; i < list.size(); i++)
				list.get(i).setFormattime(dateformat2.format(list.get(i).getNtime()));
			
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
		    request.setAttribute("newspaging", page);
			map.addAttribute("newslist", page.getDataList());
			return "newsmana";
			
		}else{
			return "adminslogin";
		}
		
		
	}
	
	//插入一条新的新闻
	@RequestMapping("/insetnews.do")
	@ResponseBody
	public ResponseResult<Void> insetnotices(HttpServletRequest request) throws Exception{
		
		String name = request.getParameter("name").trim();
		name = URLDecoder.decode(name,"UTF-8");
		
		String ntype = request.getParameter("ntype").trim();
		ntype = URLDecoder.decode(ntype,"UTF-8");
		
		String img = request.getParameter("img").trim();
		
		String content = request.getParameter("content").trim();
		content = URLDecoder.decode(content,"UTF-8");
		
		ResponseResult<Void> rr = null;
		
		int ntid = 0;
		NewsTypes newstype = newstypeservice.selectNewsTypesByname(ntype);
		if(newstype != null)
			ntid = newstype.getTid();
		
		News nes = new News();
		nes.setNcont(content);
		nes.setNtime(timestampDate());
		nes.setNtitle(name);
		nes.setNtypeid(ntid);
		nes.setNimg(img);
		
		try {
			List<News> checknews = newsService.checkNewsByname(name); 
			if(checknews.size() == 0){
				newsService.insertNews(nes);
				
				rr = new ResponseResult<Void>(1, "添加数据成功");
			}else{
				rr = new ResponseResult<Void>(1, "添加数据失败");
			}
			
		}catch(RuntimeException ex) {
			rr = new ResponseResult<Void>(0, ex.getMessage());
		}
		return rr;
	}
	
	//更新新闻
	@RequestMapping("/updatenews.do")
	@ResponseBody
	public ResponseResult<Void> updatenews(HttpServletRequest request) throws Exception{
		
		String name = request.getParameter("name").trim();
		name = URLDecoder.decode(name,"UTF-8");
		
		int tid = Integer.parseInt(request.getParameter("tid").trim());
		
		String ntype = request.getParameter("ntype").trim();
		ntype = URLDecoder.decode(ntype,"UTF-8");
		
		String content = request.getParameter("content").trim();
		content = URLDecoder.decode(content,"UTF-8");
		
		String img = request.getParameter("img").trim();
		
		ResponseResult<Void> rr = null;
		
		int ntid = 0;
		NewsTypes newstype = newstypeservice.selectNewsTypesByname(ntype);
		if(newstype != null)
			ntid = newstype.getTid();
		
		News nes = new News();
		nes.setNcont(content);
		nes.setNtitle(name);
		nes.setNtypeid(ntid);
		nes.setNid(tid);
		nes.setNimg(img);
		
		try {
			List<News> checknews = newsService.checkNewsByname(name); 
			if(checknews.size() == 0 || checknews.size() == 1){
				newsService.updateNews(nes);
				
				rr = new ResponseResult<Void>(1, "修改数据成功");
			}else{
				rr = new ResponseResult<Void>(1, "修改数据失败");
			}
			
		}catch(RuntimeException ex) {
			rr = new ResponseResult<Void>(0, ex.getMessage());
		}
		return rr;
	}
	
	//查询特定的通知
	@RequestMapping("/getNewsById.do")
	@ResponseBody
	public ResponseResult<News> getNewsById(HttpServletRequest request) throws Exception{
		
		int tid = Integer.parseInt(request.getParameter("tid").trim());
		
		ResponseResult<News> rr = null;

		try {
			News newss = newsService.selectNewsById(tid);
			if(newss != null){
				rr = new ResponseResult<>(1,"请求数据成功", newss);
			}else{
				rr = new ResponseResult<>(1, "添加数据失败");
			}
			
		}catch(RuntimeException ex) {
			rr = new ResponseResult<>(0, ex.getMessage());
		}
		return rr;
	}
	
	//获取全部的新闻类型
	@RequestMapping("/getAllNewTypessDataInfo.do")
	@ResponseBody
	public ResponseResult<List<NewsTypes>> getNoticesById(HttpServletRequest request) throws Exception{
		
		ResponseResult<List<NewsTypes>> rr = null;

		try {
			List<NewsTypes> newslist = newstypeservice.selectAllNewsTypes();
			if(newslist != null){
				rr = new ResponseResult<>(1,"请求数据成功", newslist);
			}else{
				rr = new ResponseResult<>(1, "添加数据失败");
			}
			
		}catch(RuntimeException ex) {
			rr = new ResponseResult<>(0, ex.getMessage());
		}
		return rr;
	}
	
	//根据id删除对应的新闻
	@RequestMapping("/deleteNewsById.do")
	@ResponseBody
	public ResponseResult<Void> deleteNewsById(HttpServletRequest request, HttpSession session){
		ResponseResult<Void> rr = null;
		int tid = Integer.parseInt(request.getParameter("id"));
		newsService.deleteNewsById(tid);
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
















