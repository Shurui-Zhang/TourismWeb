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
import org.springframework.web.servlet.ModelAndView;

import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.DianzanLogs;
import cn.tedu.store.bean.Notices;
import cn.tedu.store.bean.Orders;
import cn.tedu.store.bean.OrdersTotals;
import cn.tedu.store.bean.PageBean;
import cn.tedu.store.bean.Pingluns;
import cn.tedu.store.bean.ResponseResult;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;
import cn.tedu.store.service.DianzanLogsService;
import cn.tedu.store.service.IDianzanLogsService;
import cn.tedu.store.service.INoticesService;
import cn.tedu.store.service.IOrdersService;
import cn.tedu.store.service.IOrdersTotalsService;
import cn.tedu.store.service.IPinglunsService;
import cn.tedu.store.service.IScenerysService;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.OrdersTotalsService;
import cn.tedu.store.service.ScenerysService;

@Controller
@RequestMapping("/scenerys")
/**
 * 管理与景点相关的功能
 * @author LENOVO
 *
 */
public class ScenerysController extends BaseController{
	@Resource
	private IScenerysService scenerysService;
	@Resource
	private IOrdersService ordersService;
	@Resource
	private IPinglunsService pinglunsService;
	@Resource
	private IDianzanLogsService dianzanLogsService;
	@Resource
	private IOrdersTotalsService ordersTotalsService;
	
	
	//点赞
	@RequestMapping("/dianzanscenerys.do")
	@ResponseBody
	public String dianzanscenerys(HttpServletRequest request,HttpSession session) throws Exception{
		
		int sid = Integer.parseInt(request.getParameter("sid").trim());
		
		User user = (User) session.getAttribute("user");
		
		DianzanLogs dianzanLogs = new DianzanLogs();
		dianzanLogs.setLsid(sid);
		dianzanLogs.setLuid(user.getId());
		
		
		if(dianzanLogsService.checkDianzanLogs(dianzanLogs).size() == 0){
			dianzanLogsService.insertDianzanLogs(dianzanLogs);
		}
		
		int nums = 0;
		Scenerys dianzans = scenerysService.selectScenerysById(sid);
		if(dianzans != null)
			nums = dianzans.getSnums();
		
		Scenerys ss = new Scenerys();
		ss.setSnums(nums + 1);
		ss.setSid(sid);
		
		scenerysService.updateScenerysDianzanNums(ss);
		
		return "redirect:/orders/usersOrdersmana.do";
	}
	
	
	//评论
	@RequestMapping("/pinglunscenerys.do")
	@ResponseBody
	public String pinglunscenerys(HttpServletRequest request,HttpSession session) throws Exception{
		
		int sid = Integer.parseInt(request.getParameter("sid").trim());
		String pcot = request.getParameter("pcot").trim();
		pcot = URLDecoder.decode(pcot,"UTF-8");
		
		User user = (User) session.getAttribute("user");
		
		Pingluns pinglun = new Pingluns();
		pinglun.setPcont(pcot);
		pinglun.setPsid(sid);
		pinglun.setPtime(timestampDate());
		pinglun.setPuid(user.getId());
		
		pinglunsService.insertPingluns(pinglun);
		
		return "redirect:/orders/usersOrdersmana.do";
	}
	
	
	//预订
	@ResponseBody
	@RequestMapping("/orderscenerys.do")
	public Scenerys orderscenerys(HttpServletRequest request,HttpSession session) throws Exception{
		
		int sid = Integer.parseInt(request.getParameter("sid").trim());
		int onums = Integer.parseInt(request.getParameter("onums").trim());
		String otime = request.getParameter("otime").trim();
		
		User user = (User) session.getAttribute("user");
		
		OrdersTotals ordersTotals = new OrdersTotals();
		ordersTotals.setOtsid(sid);
		ordersTotals.setOttime(strFormatTimetamp(otime));
		ordersTotals.setOtnums(onums);
		
		List<OrdersTotals> checkOrdersTotals = ordersTotalsService.selectAllOrdersTotals(ordersTotals);
		if(checkOrdersTotals.size() == 0){
			if(onums <= 100 ){
				ordersTotalsService.insertOrdersTotals(ordersTotals);
				
				Orders orders = new Orders();
				orders.setOcreatetime(timestampDate());
				orders.setOnums(onums);
				orders.setOsid(sid);
				orders.setOuid(user.getId());
				orders.setOordertime(strFormatTimetamp(otime));
				
				ordersService.insertOrders(orders);
				
				return Scenerys.getJsonResult(200, "预订成功");
			}else{
				return Scenerys.getJsonResult(200, "该预订日期票数已经达到100，预订失败");
			}
		}else{
			int orderstotal = checkOrdersTotals.get(0).getOtnums();
			int endtotal = orderstotal + onums;
			if(endtotal <= 100 ){
				ordersTotals.setOtnums(endtotal);
				ordersTotalsService.updateOrdersTotals(ordersTotals);
				
				Orders orders = new Orders();
				orders.setOcreatetime(timestampDate());
				orders.setOnums(onums);
				orders.setOsid(sid);
				orders.setOuid(user.getId());
				orders.setOordertime(strFormatTimetamp(otime));
				
				ordersService.insertOrders(orders);
				
				return Scenerys.getJsonResult(200, "预订成功");
			}else{
				return Scenerys.getJsonResult(200, "该预订日期票数已经达到100，预订失败");
			}
		}
	}
	
	//进入景点详情
	@RequestMapping("/gousersScenerysDetail.do")
	public String gousersScenerysDetail(PageBean page, ModelMap map,HttpServletRequest request,HttpSession session) {
		//在查看景点详情前必须要user先登录
		if(session.getAttribute("user") != null && !session.getAttribute("user").toString().equals("")){
			int nid = Integer.parseInt(request.getParameter("nid").trim());
			
			//查询景点
			Scenerys Scenerysdetails = scenerysService.selectScenerysById(nid);
			
			SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd"); 
			Scenerysdetails.setFormattime(dateformat2.format(Scenerysdetails.getStime()));
			
			request.setAttribute("scenerysdetails", Scenerysdetails);
			
			//查询评论列表
			List<Pingluns> pinglunss = pinglunsService.selectPinglunsBySId(nid);
			
			for(int i = 0; i < pinglunss.size(); i ++)
			pinglunss.get(i).setFormattime(dateformat2.format(pinglunss.get(i).getPtime()));
			
			request.setAttribute("pinglunssdetails", pinglunss);
			//查询点赞
			User user = (User)session.getAttribute("user");
			
			DianzanLogs dianzanLogs = new DianzanLogs();
			dianzanLogs.setLsid(nid);
			dianzanLogs.setLuid(user.getId());
			
			List<DianzanLogs> dianzanlogss = dianzanLogsService.checkDianzanLogs(dianzanLogs);
			if(dianzanlogss.size() > 0)
				request.setAttribute("dianzanlogs", dianzanlogss);
			
			return "usersScenerysDetail";
			
		}else{
			return "login";
		}
	}
	
	//用户查看所有景点列表
	@RequestMapping("/usersAllSceneryss.do")
	public String usersAllSceneryss(PageBean page, ModelMap map,HttpServletRequest request,HttpSession session) {
		
		List<Scenerys> list = scenerysService.selectAllScenerys();
		SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd"); 
		for(int i = 0; i < list.size(); i++)
			list.get(i).setFormattime(dateformat2.format(list.get(i).getStime()));
		
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
		map.addAttribute("usersceneryslist", page.getDataList());
		
		return "usersAllScenerys";
	}
	
	
	
	//风景景点管理，调出景点页面
	@RequestMapping("/sceneryssmana.do")
	public String showIndex(PageBean page, ModelMap map,HttpServletRequest request,HttpSession session) {
		
		if(session.getAttribute("admins") != null && !session.getAttribute("admins").toString().equals("")){
			List<Scenerys> list = scenerysService.selectAllScenerys();
			SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd"); 
			for(int i = 0; i < list.size(); i++)
				list.get(i).setFormattime(dateformat2.format(list.get(i).getStime()));
			
			pagingindex(page,list,request);
			
			map.addAttribute("sceneryslist", page.getDataList());
			return "sceneryssmana";			
		}else{
			return "adminslogin";
		}
		
	}
	
	//模糊查询景点
	@RequestMapping("/mohusceneryssmana.do")
	public String mohusceneryssmana(PageBean page, ModelMap map,HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException {
			//前端页面传参为stitle,去掉前后空格
			String stitle = request.getParameter("stitle").trim();
			stitle = URLDecoder.decode(stitle,"UTF-8");
			
			String endName = "%"+stitle+"%";
			
			List<Scenerys> list = scenerysService.selectScenerysListsByname(endName);
			SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy-MM-dd"); 
			for(int i = 0; i < list.size(); i++)
				list.get(i).setFormattime(dateformat2.format(list.get(i).getStime()));
			
			//进行分页
			pagingindex(page,list,request);
			
			map.addAttribute("usersceneryslist", page.getDataList());
			return "usersAllScenerys";			
	}
	
	
	//插入一个新的景点
	@RequestMapping("/insetscenerys.do")
	@ResponseBody
	public ResponseResult<Void> insetnotices(HttpServletRequest request) throws Exception{
		
		String name = request.getParameter("name").trim();
		name = URLDecoder.decode(name,"UTF-8");
		
		String attr = request.getParameter("attr").trim();
		attr = URLDecoder.decode(attr,"UTF-8");
		
		String surl = request.getParameter("surl").trim();
		surl = URLDecoder.decode(surl,"UTF-8");
		
		String img = request.getParameter("img").trim();
		
		String content = request.getParameter("content").trim();
		content = URLDecoder.decode(content,"UTF-8");
		
		ResponseResult<Void> rr = null;
		
		Scenerys scenerys = new Scenerys();
		scenerys.setSattr(attr);
		scenerys.setScont(content);
		scenerys.setSimg("");
		scenerys.setStime(timestampDate());
		scenerys.setStitle(name);
		scenerys.setSurl(surl);
		scenerys.setSimg(img);
		
		try {
			List<Scenerys> checkNts = scenerysService.checkScenerysByname(name);
			if(checkNts.size() == 0){
				scenerysService.insertScenerys(scenerys);
				
				rr = new ResponseResult<Void>(1, "添加数据成功");
			}else{
				rr = new ResponseResult<Void>(1, "添加数据失败");
			}
			
		}catch(RuntimeException ex) {
			rr = new ResponseResult<Void>(0, ex.getMessage());
		}
		return rr;
	}
	
	//编辑景点
	@RequestMapping("/updatescenerys.do")
	@ResponseBody
	public ResponseResult<Void> updatenotices(HttpServletRequest request) throws Exception{
		
		String name = request.getParameter("name").trim();
		name = URLDecoder.decode(name,"UTF-8");
		
		String attr = request.getParameter("attr").trim();
		attr = URLDecoder.decode(attr,"UTF-8");
		
		String surl = request.getParameter("surl").trim();
		surl = URLDecoder.decode(surl,"UTF-8");
		
		String img = request.getParameter("img").trim();
		
		String content = request.getParameter("content").trim();
		content = URLDecoder.decode(content,"UTF-8");
		
		int tid = Integer.parseInt(request.getParameter("tid").trim());
		
		ResponseResult<Void> rr = null;
		
		Scenerys scenerys = new Scenerys();
		scenerys.setSattr(attr);
		scenerys.setScont(content);
		scenerys.setSimg("");
		scenerys.setStitle(name);
		scenerys.setSurl(surl);
		scenerys.setSid(tid);
		scenerys.setSimg(img);
		
		try {
			List<Scenerys> checkNts = scenerysService.checkScenerysByname(name);
			if(checkNts.size() == 0 || checkNts.size() == 1){
				scenerysService.updateScenerys(scenerys);
				
				rr = new ResponseResult<Void>(1, "修改数据成功");
			}else{
				rr = new ResponseResult<Void>(1, "修改数据失败");
			}
			
		}catch(RuntimeException ex) {
			rr = new ResponseResult<Void>(0, ex.getMessage());
		}
		return rr;
	}
	
	
	@RequestMapping("/getScenerysById.do")
	@ResponseBody
	public ResponseResult<Scenerys> getNoticesById(HttpServletRequest request) throws Exception{
		
		int tid = Integer.parseInt(request.getParameter("tid").trim());
		
		ResponseResult<Scenerys> rr = null;

		try {
			Scenerys scenerys = scenerysService.selectScenerysById(tid);
			if(scenerys != null){
				rr = new ResponseResult<>(1,"请求数据成功", scenerys);
			}else{
				rr = new ResponseResult<>(1, "添加数据失败");
			}
			
		}catch(RuntimeException ex) {
			rr = new ResponseResult<>(0, ex.getMessage());
		}
		return rr;
	}
	
	//删除景点
	@RequestMapping("/deleteScenerysById.do")
	@ResponseBody
	public ResponseResult<Void> deleteNoticeById(HttpServletRequest request, HttpSession session){
		ResponseResult<Void> rr = null;
		int tid = Integer.parseInt(request.getParameter("id"));
		scenerysService.deleteScenerysById(tid);
		rr = new ResponseResult<>(1, "删除成功");
		return rr;
	}
	
	
	public <T> void pagingindex(PageBean page,List<T> list,HttpServletRequest request){
		//初始化当前页
		if (page.getCurrentPage() == null){
	        page.setCurrentPage(1);
	    } else {
	        page.setCurrentPage(page.getCurrentPage());
	    }
	    //设置一页放多少个
	    page.setPageSize(5);
	    //设置每一页开始的list元素
	    page.setStar((page.getCurrentPage() - 1) * page.getPageSize());
	    //list中元素个数
	    int count = list.size();
	    //获取总页数，每页放五个
	    page.setTotalPage(count % 5 == 0 ? count / 5 : count / 5 + 1);
	    //确定出当前页放哪些项目
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
















