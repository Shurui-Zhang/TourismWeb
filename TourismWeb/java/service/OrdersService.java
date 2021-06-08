package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tedu.store.bean.News;
import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Orders;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.NewsMapper;
import cn.tedu.store.mapper.NewsTypesMapper;
import cn.tedu.store.mapper.OrdersMapper;
import cn.tedu.store.mapper.ScenerysMapper;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistException;
@Service
public class OrdersService implements IOrdersService{
	
	@Resource
	private OrdersMapper mapper;

	@Override
	public List<Orders> selectAllOrders() {
		// TODO Auto-generated method stub
		return mapper.selectAllOrders();
	}

	@Override
	public List<Orders> selectOrdersById(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectOrdersById(id);
	}

	@Override
	public void deleteOrdersById(Integer id) {
		// TODO Auto-generated method stub
		mapper.deleteOrdersById(id);
	}

	@Override
	public void insertOrders(Orders orders) {
		// TODO Auto-generated method stub
		mapper.insertOrders(orders);
	}

	@Override
	public void updateOrders(Orders orders) {
		// TODO Auto-generated method stub
		mapper.updateOrders(orders);
	}



	
	
	
	
}

















