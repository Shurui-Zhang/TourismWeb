package cn.tedu.store.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Pingluns implements Serializable{
	private static final long serialVersionUID = 5192452455532582657L;
	
	private Integer Pid;
	private Integer Psid;
	private Integer Puid;
	private String Pcont;
	private Timestamp Ptime;
	private String formattime;
	private int Pnums;
	
	private String scenerystitle;
	private String scenerysimg;
	private String usersname;
	private String userimg;
	
	
	
	
	public int getPnums() {
		return Pnums;
	}
	public void setPnums(int pnums) {
		Pnums = pnums;
	}
	public String getUserimg() {
		return userimg;
	}
	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}
	public Integer getPid() {
		return Pid;
	}
	public void setPid(Integer pid) {
		Pid = pid;
	}
	public Integer getPsid() {
		return Psid;
	}
	public void setPsid(Integer psid) {
		Psid = psid;
	}
	public Integer getPuid() {
		return Puid;
	}
	public void setPuid(Integer puid) {
		Puid = puid;
	}
	public String getPcont() {
		return Pcont;
	}
	public void setPcont(String pcont) {
		Pcont = pcont;
	}
	public Timestamp getPtime() {
		return Ptime;
	}
	public void setPtime(Timestamp ptime) {
		Ptime = ptime;
	}
	public String getFormattime() {
		return formattime;
	}
	public void setFormattime(String formattime) {
		this.formattime = formattime;
	}
	public String getScenerystitle() {
		return scenerystitle;
	}
	public void setScenerystitle(String scenerystitle) {
		this.scenerystitle = scenerystitle;
	}
	public String getScenerysimg() {
		return scenerysimg;
	}
	public void setScenerysimg(String scenerysimg) {
		this.scenerysimg = scenerysimg;
	}
	public String getUsersname() {
		return usersname;
	}
	public void setUsersname(String usersname) {
		this.usersname = usersname;
	}
	
	
	
	

	
}
