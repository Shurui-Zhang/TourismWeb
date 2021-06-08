package cn.tedu.store.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Notices implements Serializable{
	private static final long serialVersionUID = 5192452455532582657L;
	
	private Integer Nid;
	private String Ntitle;
	private Timestamp Ntime;
	private String formattime;//将数据库中的time取出后改变格式存入formattime
	private String Ncont;
	
	
	public Integer getNid() {
		return Nid;
	}
	public void setNid(Integer nid) {
		Nid = nid;
	}
	public String getNtitle() {
		return Ntitle;
	}
	public void setNtitle(String ntitle) {
		Ntitle = ntitle;
	}
	public Timestamp getNtime() {
		return Ntime;
	}
	public void setNtime(Timestamp ntime) {
		Ntime = ntime;
	}
	public String getFormattime() {
		return formattime;
	}
	public void setFormattime(String formattime) {
		this.formattime = formattime;
	}
	public String getNcont() {
		return Ncont;
	}
	public void setNcont(String ncont) {
		Ncont = ncont;
	}
	
	
	
	
	

	
}
