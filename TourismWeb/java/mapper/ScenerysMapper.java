package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;

public interface ScenerysMapper {
	/*
	 * 查询全部景点
	 */
	List<Scenerys> selectAllScenerys();
	
	List<Scenerys> selectScenerysCount();
	/*
	 * 通过id查询景点信息
	 */
	Scenerys selectScenerysById(Integer id);
	
	Scenerys selectScenerysByname(String tname);

	List<Scenerys> checkScenerysByname(String tname);
	
	void deleteScenerysById(Integer id);
	
	void insertScenerys(Scenerys scenerys);
	
	void updateScenerys(Scenerys scenerys);
	/*
	 * 查询前六个景点
	 */
	List<Scenerys> selectAllScenerys6();
	/*
	 * 模糊查询景点，根据景点名称与地址
	 */
	List<Scenerys> selectScenerysListsByname(String tname);
	
	void updateScenerysDianzanNums(Scenerys scenerys);

}






















