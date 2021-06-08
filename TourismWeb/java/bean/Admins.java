package cn.tedu.store.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Admins implements Serializable{
	private static final long serialVersionUID = 5192452455532582657L;
	
	private Integer Aid;
	private String Aname;
	private String Apwd;
	private String Atel;
	private String Aimg;
	
	
	private int code;
	private String msg;
	private Object obj;
	
	public Admins(){
		
	}
	
	public Admins(int code,String msg,Object obj){
		this.code=code;
		this.msg=msg;
		this.obj=obj;
	}
	
	public Admins(int code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	public static Admins getJsonResult(int code,String msg,Object obj){
		return new Admins(code,msg,obj);
	}
	
	public static Admins getJsonResult(int code,String msg){
		return new Admins(code,msg);
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

	public Integer getAid() {
		return Aid;
	}
	public void setAid(Integer aid) {
		Aid = aid;
	}
	public String getAname() {
		return Aname;
	}
	public void setAname(String aname) {
		Aname = aname;
	}
	public String getApwd() {
		return Apwd;
	}
	public void setApwd(String apwd) {
		Apwd = apwd;
	}
	public String getAtel() {
		return Atel;
	}
	public void setAtel(String atel) {
		Atel = atel;
	}
	public String getAimg() {
		return Aimg;
	}
	public void setAimg(String aimg) {
		Aimg = aimg;
	}
	
	
	
	

	
}
