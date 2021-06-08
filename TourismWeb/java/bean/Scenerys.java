package cn.tedu.store.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Scenerys implements Serializable{
	private static final long serialVersionUID = 5192452455532582657L;
	
	private Integer Sid;
	private String Stitle;
	private String Scont;
	private String Simg;
	private String Sattr;
	private String Surl;
	private int Snums;
	
	private Timestamp Stime;
	private String formattime;
	
	private int code;
	private String msg;
	private Object obj;
	
	public Scenerys(){
		
	}
	
	public Scenerys(int code,String msg,Object obj){
		this.code=code;
		this.msg=msg;
		this.obj=obj;
	}
	
	public Scenerys(int code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	public static Scenerys getJsonResult(int code,String msg,Object obj){
		return new Scenerys(code,msg,obj);
	}
	
	public static Scenerys getJsonResult(int code,String msg){
		return new Scenerys(code,msg);
	}
	
	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public int getSnums() {
		return Snums;
	}
	public void setSnums(int snums) {
		Snums = snums;
	}
	public Integer getSid() {
		return Sid;
	}
	public void setSid(Integer sid) {
		Sid = sid;
	}
	public String getStitle() {
		return Stitle;
	}
	public void setStitle(String stitle) {
		Stitle = stitle;
	}
	public String getScont() {
		return Scont;
	}
	public void setScont(String scont) {
		Scont = scont;
	}
	public String getSimg() {
		return Simg;
	}
	public void setSimg(String simg) {
		Simg = simg;
	}
	public String getSattr() {
		return Sattr;
	}
	public void setSattr(String sattr) {
		Sattr = sattr;
	}
	public String getSurl() {
		return Surl;
	}
	public void setSurl(String surl) {
		Surl = surl;
	}
	public Timestamp getStime() {
		return Stime;
	}
	public void setStime(Timestamp stime) {
		Stime = stime;
	}
	public String getFormattime() {
		return formattime;
	}
	public void setFormattime(String formattime) {
		this.formattime = formattime;
	}
	
	
	
	
	

	
}
