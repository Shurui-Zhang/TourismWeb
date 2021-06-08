package cn.tedu.store.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Orders implements Serializable{
	private static final long serialVersionUID = 5192452455532582657L;
	
	private Integer Oid;
	private Integer Osid;
	private Integer Ouid;
	private Integer Onums;
	private Timestamp Ocreatetime;
	private String createformattime;
	private Timestamp Oordertime;
	private String orderformattime;
	
	private String scenerystitle;
	private String scenerysimg;
	private String usersname;
	
	
	
	public String getScenerysimg() {
		return scenerysimg;
	}
	public void setScenerysimg(String scenerysimg) {
		this.scenerysimg = scenerysimg;
	}
	public Integer getOid() {
		return Oid;
	}
	public void setOid(Integer oid) {
		Oid = oid;
	}
	public Integer getOsid() {
		return Osid;
	}
	public void setOsid(Integer osid) {
		Osid = osid;
	}
	public Integer getOuid() {
		return Ouid;
	}
	public void setOuid(Integer ouid) {
		Ouid = ouid;
	}
	public Integer getOnums() {
		return Onums;
	}
	public void setOnums(Integer onums) {
		Onums = onums;
	}
	public Timestamp getOcreatetime() {
		return Ocreatetime;
	}
	public void setOcreatetime(Timestamp ocreatetime) {
		Ocreatetime = ocreatetime;
	}
	public String getCreateformattime() {
		return createformattime;
	}
	public void setCreateformattime(String createformattime) {
		this.createformattime = createformattime;
	}
	public Timestamp getOordertime() {
		return Oordertime;
	}
	public void setOordertime(Timestamp oordertime) {
		Oordertime = oordertime;
	}
	public String getOrderformattime() {
		return orderformattime;
	}
	public void setOrderformattime(String orderformattime) {
		this.orderformattime = orderformattime;
	}
	public String getScenerystitle() {
		return scenerystitle;
	}
	public void setScenerystitle(String scenerystitle) {
		this.scenerystitle = scenerystitle;
	}
	public String getUsersname() {
		return usersname;
	}
	public void setUsersname(String usersname) {
		this.usersname = usersname;
	}
	
	
	
	
	

	
}
