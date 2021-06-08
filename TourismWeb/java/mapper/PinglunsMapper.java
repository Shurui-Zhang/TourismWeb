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
	 * ͨ��id��ѯ����
	 */
	void deletePinglunsById(Integer id);
	/*
	 * ��������
	 */
	void insertPingluns(Pingluns pingluns);
	
	List<Pingluns> selectPinglunsBySId(Integer id);
	

}






















