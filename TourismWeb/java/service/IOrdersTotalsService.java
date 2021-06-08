package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.News;
import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Orders;
import cn.tedu.store.bean.OrdersTotals;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;

public interface IOrdersTotalsService {

	List<OrdersTotals> selectAllOrdersTotals(OrdersTotals ordersTotals);
	
	void insertOrdersTotals(OrdersTotals ordersTotals);
	
	void updateOrdersTotals(OrdersTotals ordersTotals);
	
}











