package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.News;
import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Orders;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;

public interface IOrdersService {

	List<Orders> selectAllOrders();
	
	List<Orders> selectOrdersById(Integer id);
	
	void deleteOrdersById(Integer id);
	
	void insertOrders(Orders orders);
	
	void updateOrders(Orders orders);
	
}











