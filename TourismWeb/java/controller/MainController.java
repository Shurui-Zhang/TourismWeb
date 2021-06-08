package cn.tedu.store.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.store.bean.News;
import cn.tedu.store.bean.Notices;
import cn.tedu.store.bean.PageBean;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.service.INewsService;
import cn.tedu.store.service.INoticesService;
import cn.tedu.store.service.IScenerysService;
import cn.tedu.store.service.ScenerysService;

@Controller
@RequestMapping("/main")
public class MainController {
	
	@Resource
	private INoticesService noticesService;
	@Resource
	private INewsService newsService;
	@Resource
	private IScenerysService scenerysService;
	

	//显示首页
	@RequestMapping("/showIndex.do")
	public String showIndex(PageBean page, ModelMap map,HttpServletRequest request) {
		//查找出全部的通知
		List<Notices> list = noticesService.selectAllNotices();
		//调整date的格式
		SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy-MM-dd"); 
		for(int i = 0; i < list.size(); i++)
			list.get(i).setFormattime(dateformat2.format(list.get(i).getNtime()));
		
		map.addAttribute("indexlist", list);
		
		//查找前六个新闻
		List<News> newslist = newsService.selectAllNews6(); 
		for(int i = 0; i < newslist.size(); i++)
			newslist.get(i).setFormattime(
					dateformat2.format(newslist.get(i).getNtime()));
		
		map.addAttribute("indexnewslist", newslist);
		
		//查询前六个景点
		List<Scenerys> sceneryslist = scenerysService.selectAllScenerys6();
		for(int i = 0; i < sceneryslist.size(); i++)
			sceneryslist.get(i).setFormattime(
					dateformat2.format(sceneryslist.get(i).getStime()));
		
		map.addAttribute("indexsceneryslist", sceneryslist);
		
		return "index";
	}
	//分页
	public <T> void pagingindex(PageBean page,List<T> list,HttpServletRequest request){
		if (page.getCurrentPage() == null){
	        page.setCurrentPage(1);
	    } else {
	        page.setCurrentPage(page.getCurrentPage());
	    }
	    
	    page.setPageSize(10);
	    page.setStar((page.getCurrentPage() - 1) * page.getPageSize());
	    int count = list.size();
	    page.setTotalPage(count % 10 == 0 ? count / 10 : count / 10 + 1);
	    page.setDataList(list.subList(page.getStar(),count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
	    request.setAttribute("paging", page);  
	}
	
	public <T> void pagingindex2(PageBean page,List<T> list,HttpServletRequest request){
		if (page.getCurrentPage() == null){
	        page.setCurrentPage(1);
	    } else {
	        page.setCurrentPage(page.getCurrentPage());
	    }
	    
	    page.setPageSize(10);
	    page.setStar((page.getCurrentPage() - 1) * page.getPageSize());
	    int count = list.size();
	    page.setTotalPage(count % 10 == 0 ? count / 10 : count / 10 + 1);
	    page.setDataList(list.subList(page.getStar(),count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
	    request.setAttribute("paging2", page);  
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






















