package cn.tedu.store.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class News implements Serializable{
	private static final long serialVersionUID = 5192452455532582657L;
	
	private Integer Nid;
	private String Ntitle;
	private String Nimg;
	private String Ncont;
	private Integer Ntypeid;
	
	private Timestamp Ntime;
	private String formattime;
	
	private String newtype;//关联查询外表字段
	
	
	
	public String getNewtype() {
		return newtype;
	}
	public void setNewtype(String newtype) {
		this.newtype = newtype;
	}
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
	public String getNimg() {
		return Nimg;
	}
	public void setNimg(String nimg) {
		Nimg = nimg;
	}
	public String getNcont() {
		return Ncont;
	}
	public void setNcont(String ncont) {
		Ncont = ncont;
	}
	public Integer getNtypeid() {
		return Ntypeid;
	}
	public void setNtypeid(Integer ntypeid) {
		Ntypeid = ntypeid;
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
	
	
	
	
	
	

	
}
