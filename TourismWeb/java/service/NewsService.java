package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tedu.store.bean.News;
import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.NewsMapper;
import cn.tedu.store.mapper.NewsTypesMapper;
import cn.tedu.store.mapper.ScenerysMapper;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistException;
@Service
public class NewsService implements INewsService{
	
	@Resource
	private NewsMapper mapper;

	@Override
	public List<News> selectAllNews() {
		// TODO Auto-generated method stub
		return mapper.selectAllNews();
	}

	@Override
	public List<News> selectNewsCount() {
		// TODO Auto-generated method stub
		return mapper.selectNewsCount();
	}

	@Override
	public News selectNewsById(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectNewsById(id);
	}

	@Override
	public News selectNewsByname(String tname) {
		// TODO Auto-generated method stub
		return mapper.selectNewsByname(tname);
	}

	@Override
	public List<News> checkNewsByname(String tname) {
		// TODO Auto-generated method stub
		return mapper.checkNewsByname(tname);
	}

	@Override
	public void deleteNewsById(Integer id) {
		// TODO Auto-generated method stub
		mapper.deleteNewsById(id);
	}

	@Override
	public void insertNews(News news) {
		// TODO Auto-generated method stub
		mapper.insertNews(news);
	}

	@Override
	public void updateNews(News news) {
		// TODO Auto-generated method stub
		mapper.updateNews(news);
	}

	@Override
	public List<News> selectAllNews6() {
		// TODO Auto-generated method stub
		return mapper.selectAllNews6();
	}

	

	
	
	
	
}

















