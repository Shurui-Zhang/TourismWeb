package cn.tedu.store.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class DianzanLogs implements Serializable{
	private static final long serialVersionUID = 5192452455532582657L;
	
	private Integer Lid;
	private Integer Lsid;
	private Integer Luid;
	
	
	public Integer getLid() {
		return Lid;
	}
	public void setLid(Integer lid) {
		Lid = lid;
	}
	public Integer getLsid() {
		return Lsid;
	}
	public void setLsid(Integer lsid) {
		Lsid = lsid;
	}
	public Integer getLuid() {
		return Luid;
	}
	public void setLuid(Integer luid) {
		Luid = luid;
	}
	
	
	

	
}
