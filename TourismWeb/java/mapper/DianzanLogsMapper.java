package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Orders;
import cn.tedu.store.bean.Pingluns;
import cn.tedu.store.bean.DianzanLogs;
import cn.tedu.store.bean.News;
import cn.tedu.store.bean.User;

public interface DianzanLogsMapper {
	
	List<DianzanLogs> checkDianzanLogs(DianzanLogs dianzanLogs);
	
	void insertDianzanLogs(DianzanLogs dianzanLogs);
	

}






















