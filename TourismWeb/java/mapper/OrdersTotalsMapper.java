package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Orders;
import cn.tedu.store.bean.OrdersTotals;
import cn.tedu.store.bean.News;
import cn.tedu.store.bean.User;

public interface OrdersTotalsMapper {
	
	List<OrdersTotals> selectAllOrdersTotals(OrdersTotals ordersTotals);
	
	void insertOrdersTotals(OrdersTotals ordersTotals);
	
	void updateOrdersTotals(OrdersTotals ordersTotals);
	
	
	

}






















