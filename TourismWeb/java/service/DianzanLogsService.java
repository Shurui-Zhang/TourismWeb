package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tedu.store.bean.DianzanLogs;
import cn.tedu.store.bean.News;
import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Orders;
import cn.tedu.store.bean.Pingluns;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.DianzanLogsMapper;
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
public class DianzanLogsService implements IDianzanLogsService{
	
	@Resource
	private DianzanLogsMapper mapper;

	@Override
	public List<DianzanLogs> checkDianzanLogs(DianzanLogs dianzanLogs) {
		// TODO Auto-generated method stub
		return mapper.checkDianzanLogs(dianzanLogs);
	}

	@Override
	public void insertDianzanLogs(DianzanLogs dianzanLogs) {
		// TODO Auto-generated method stub
		mapper.insertDianzanLogs(dianzanLogs);
	}

	

	
	
	
}

















