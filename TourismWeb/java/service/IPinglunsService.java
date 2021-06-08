package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.News;
import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Orders;
import cn.tedu.store.bean.Pingluns;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;

public interface IPinglunsService {

	List<Pingluns> selectAllPingluns();
	
	void deletePinglunsById(Integer id);
	
	void insertPingluns(Pingluns pingluns);
	
	List<Pingluns> selectPinglunsBySId(Integer id);
	
}











