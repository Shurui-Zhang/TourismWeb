package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Orders;
import cn.tedu.store.bean.News;
import cn.tedu.store.bean.User;

public interface OrdersMapper {
	
	List<Orders> selectAllOrders();
	
	List<Orders> selectOrdersById(Integer id);
	
	void deleteOrdersById(Integer id);
	
	void insertOrders(Orders orders);
	
	void updateOrders(Orders orders);
	
	
	

}






















