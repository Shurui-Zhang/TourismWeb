package cn.tedu.store.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class NewsTypes implements Serializable{
	private static final long serialVersionUID = 5192452455532582657L;
	
	private Integer Tid;
	private String Tname;
	private Timestamp Ttime;
	private String formattime;
	
	
	
	public String getFormattime() {
		return formattime;
	}
	public void setFormattime(String formattime) {
		this.formattime = formattime;
	}
	public Integer getTid() {
		return Tid;
	}
	public void setTid(Integer tid) {
		Tid = tid;
	}
	public String getTname() {
		return Tname;
	}
	public void setTname(String tname) {
		Tname = tname;
	}
	public Timestamp getTtime() {
		return Ttime;
	}
	public void setTtime(Timestamp ttime) {
		Ttime = ttime;
	}
	
	

	
}
