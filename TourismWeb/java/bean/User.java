package cn.tedu.store.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class User implements Serializable{
	private static final long serialVersionUID = 5192452455532582657L;
	private Integer id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String image;
	private String gender;
	private Timestamp createdTime;
	private String formattime;
	
	private int code;
	private String msg;
	private Object obj;
	
	public User(){
		
	}
	
	public User(int code,String msg,Object obj){
		this.code=code;
		this.msg=msg;
		this.obj=obj;
	}
	
	public User(int code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	public static User getJsonResult(int code,String msg,Object obj){
		return new User(code,msg,obj);
	}
	
	public static User getJsonResult(int code,String msg){
		return new User(code,msg);
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

	public String getFormattime() {
		return formattime;
	}
	public void setFormattime(String formattime) {
		this.formattime = formattime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	
	
	

	
}
