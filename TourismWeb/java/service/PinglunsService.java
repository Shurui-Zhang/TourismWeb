package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tedu.store.bean.News;
import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Orders;
import cn.tedu.store.bean.Pingluns;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.NewsMapper;
import cn.tedu.store.mapper.NewsTypesMapper;
import cn.tedu.store.mapper.OrdersMapper;
import cn.tedu.store.mapper.PinglunsMapper;
import cn.tedu.store.mapper.ScenerysMapper;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistException;
@Service
public class PinglunsService implements IPinglunsService{
	
	@Resource
	private PinglunsMapper mapper;

	@Override
	public List<Pingluns> selectAllPingluns() {
		// TODO Auto-generated method stub
		return mapper.selectAllPingluns();
	}

	@Override
	public void deletePinglunsById(Integer id) {
		// TODO Auto-generated method stub
		mapper.deletePinglunsById(id);
	}

	@Override
	public void insertPingluns(Pingluns pingluns) {
		// TODO Auto-generated method stub
		mapper.insertPingluns(pingluns);
	}

	@Override
	public List<Pingluns> selectPinglunsBySId(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectPinglunsBySId(id);
	}


	
	
	
}

















