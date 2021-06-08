package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.NewsTypesMapper;
import cn.tedu.store.mapper.ScenerysMapper;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistException;
@Service
public class ScenerysService implements IScenerysService{
	
	@Resource
	private ScenerysMapper mapper;

	@Override
	public List<Scenerys> selectAllScenerys() {
		// TODO Auto-generated method stub
		return mapper.selectAllScenerys();
	}

	@Override
	public List<Scenerys> selectScenerysCount() {
		// TODO Auto-generated method stub
		return mapper.selectScenerysCount();
	}

	@Override
	public Scenerys selectScenerysById(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectScenerysById(id);
	}

	@Override
	public Scenerys selectScenerysByname(String tname) {
		// TODO Auto-generated method stub
		return mapper.selectScenerysByname(tname);
	}

	@Override
	public List<Scenerys> checkScenerysByname(String tname) {
		// TODO Auto-generated method stub
		return mapper.checkScenerysByname(tname);
	}

	@Override
	public void deleteScenerysById(Integer id) {
		// TODO Auto-generated method stub
		mapper.deleteScenerysById(id);
	}

	@Override
	public void insertScenerys(Scenerys scenerys) {
		// TODO Auto-generated method stub
		mapper.insertScenerys(scenerys);
	}

	@Override
	public void updateScenerys(Scenerys scenerys) {
		// TODO Auto-generated method stub
		mapper.updateScenerys(scenerys);
	}

	@Override
	public List<Scenerys> selectAllScenerys6() {
		// TODO Auto-generated method stub
		return mapper.selectAllScenerys6();
	}

	@Override
	public List<Scenerys> selectScenerysListsByname(String tname) {
		// TODO Auto-generated method stub
		return mapper.selectScenerysListsByname(tname);
	}

	@Override
	public void updateScenerysDianzanNums(Scenerys scenerys) {
		// TODO Auto-generated method stub
		mapper.updateScenerysDianzanNums(scenerys);
	}

	
	
	
	
}

















