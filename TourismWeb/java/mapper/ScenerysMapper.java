package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Scenerys;
import cn.tedu.store.bean.User;

public interface ScenerysMapper {
	/*
	 * ��ѯȫ������
	 */
	List<Scenerys> selectAllScenerys();
	
	List<Scenerys> selectScenerysCount();
	/*
	 * ͨ��id��ѯ������Ϣ
	 */
	Scenerys selectScenerysById(Integer id);
	
	Scenerys selectScenerysByname(String tname);

	List<Scenerys> checkScenerysByname(String tname);
	
	void deleteScenerysById(Integer id);
	
	void insertScenerys(Scenerys scenerys);
	
	void updateScenerys(Scenerys scenerys);
	/*
	 * ��ѯǰ��������
	 */
	List<Scenerys> selectAllScenerys6();
	/*
	 * ģ����ѯ���㣬���ݾ����������ַ
	 */
	List<Scenerys> selectScenerysListsByname(String tname);
	
	void updateScenerysDianzanNums(Scenerys scenerys);

}






















