package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.NewsTypesMapper;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistException;
@Service
public class NewsTypesService implements INewsTypesService{
	
	@Resource
	private NewsTypesMapper mapper;

	@Override
	public NewsTypes selectNewsTypesByname(String tname) {
		// TODO Auto-generated method stub
		return mapper.selectNewsTypesByname(tname);
	}

	@Override
	public List<NewsTypes> checkNewsTypesByname(String tname) {
		// TODO Auto-generated method stub
		return mapper.checkNewsTypesByname(tname);
	}

	@Override
	public void deleteNewsTypesById(Integer id) {
		// TODO Auto-generated method stub
		mapper.deleteNewsTypesById(id);
	}

	@Override
	public void insertNewsTypes(NewsTypes newsTypes) {
		// TODO Auto-generated method stub
		mapper.insertNewsTypes(newsTypes);
	}

	@Override
	public void updateNewsTypes(NewsTypes newsTypes) {
		// TODO Auto-generated method stub
		mapper.updateNewsTypes(newsTypes);
	}

	@Override
	public List<NewsTypes> selectAllNewsTypes() {
		// TODO Auto-generated method stub
		return mapper.selectAllNewsTypes();
	}

	@Override
	public List<NewsTypes> selectNewsTypesCount() {
		// TODO Auto-generated method stub
		return mapper.selectNewsTypesCount();
	}

	@Override
	public NewsTypes selectNewsTypesById(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectNewsTypesById(id);
	}
	
	
	
}

















