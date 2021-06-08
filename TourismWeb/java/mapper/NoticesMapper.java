package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Notices;
import cn.tedu.store.bean.User;

public interface NoticesMapper {
	/*
	 * 查找全部的通知
	 */
	List<Notices> selectAllNotices();
	
	List<Notices> selectNoticesCount();
	
	Notices selectNoticesById(Integer id);
	
	Notices selectNoticesByname(String tname);

	List<Notices> checkNoticesByname(String tname);
	
	void deleteNoticesById(Integer id);
	
	void insertNotices(Notices notices);
	
	void updateNotices(Notices notices);
}






















