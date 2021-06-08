package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.News;
import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;

public interface INewsService {

	List<News> selectAllNews();
	
	List<News> selectNewsCount();
	
	News selectNewsById(Integer id);
	
	News selectNewsByname(String tname);

	List<News> checkNewsByname(String tname);
	
	void deleteNewsById(Integer id);
	
	void insertNews(News news);
	
	void updateNews(News news);
	
	List<News> selectAllNews6();
	
}











