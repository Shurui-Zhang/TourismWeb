package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;

public interface IScenerysService {

	List<Scenerys> selectAllScenerys();
	
	List<Scenerys> selectScenerysCount();
	
	Scenerys selectScenerysById(Integer id);
	
	Scenerys selectScenerysByname(String tname);

	List<Scenerys> checkScenerysByname(String tname);
	
	void deleteScenerysById(Integer id);
	
	void insertScenerys(Scenerys scenerys);
	
	void updateScenerys(Scenerys scenerys);
	
	List<Scenerys> selectAllScenerys6();
	
	List<Scenerys> selectScenerysListsByname(String tname);
	
	void updateScenerysDianzanNums(Scenerys scenerys);
	
}











