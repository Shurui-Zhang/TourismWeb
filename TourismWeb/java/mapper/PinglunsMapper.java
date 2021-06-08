package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Orders;
import cn.tedu.store.bean.Pingluns;
import cn.tedu.store.bean.News;
import cn.tedu.store.bean.User;

public interface PinglunsMapper {
	
	List<Pingluns> selectAllPingluns();
	/*
	 * 通过id查询评论
	 */
	void deletePinglunsById(Integer id);
	/*
	 * 插入评论
	 */
	void insertPingluns(Pingluns pingluns);
	
	List<Pingluns> selectPinglunsBySId(Integer id);
	

}






















