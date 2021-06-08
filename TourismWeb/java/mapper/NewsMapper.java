package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.News;
import cn.tedu.store.bean.User;

public interface NewsMapper {
	/*
	 * 查找全部新闻
	 */
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






















