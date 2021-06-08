package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tedu.store.bean.News;
import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Orders;
import cn.tedu.store.bean.OrdersTotals;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.NewsMapper;
import cn.tedu.store.mapper.NewsTypesMapper;
import cn.tedu.store.mapper.OrdersMapper;
import cn.tedu.store.mapper.OrdersTotalsMapper;
import cn.tedu.store.mapper.ScenerysMapper;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistException;
@Service
public class OrdersTotalsService implements IOrdersTotalsService{
	
	@Resource
	private OrdersTotalsMapper mapper;

	@Override
	public List<OrdersTotals> selectAllOrdersTotals(OrdersTotals ordersTotals) {
		// TODO Auto-generated method stub
		return mapper.selectAllOrdersTotals(ordersTotals);
	}

	@Override
	public void insertOrdersTotals(OrdersTotals ordersTotals) {
		// TODO Auto-generated method stub
		mapper.insertOrdersTotals(ordersTotals);
	}

	@Override
	public void updateOrdersTotals(OrdersTotals ordersTotals) {
		// TODO Auto-generated method stub
		mapper.updateOrdersTotals(ordersTotals);
	}

	


	
	
	
	
}

















