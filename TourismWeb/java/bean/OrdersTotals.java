package cn.tedu.store.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class OrdersTotals implements Serializable{
	private static final long serialVersionUID = 5192452455532582657L;
	
	private Integer Otid;
	private Integer Otsid;
	private Integer Otnums;
	private Timestamp Ottime;
	private String orderformattime;
	
	
	public Integer getOtid() {
		return Otid;
	}
	public void setOtid(Integer otid) {
		Otid = otid;
	}
	public Integer getOtsid() {
		return Otsid;
	}
	public void setOtsid(Integer otsid) {
		Otsid = otsid;
	}
	public Integer getOtnums() {
		return Otnums;
	}
	public void setOtnums(Integer otnums) {
		Otnums = otnums;
	}
	public Timestamp getOttime() {
		return Ottime;
	}
	public void setOttime(Timestamp ottime) {
		Ottime = ottime;
	}
	public String getOrderformattime() {
		return orderformattime;
	}
	public void setOrderformattime(String orderformattime) {
		this.orderformattime = orderformattime;
	}
	

	
	

	
}
