package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Notices;
import cn.tedu.store.bean.User;

public interface INoticesService {

	List<Notices> selectAllNotices();
	
	List<Notices> selectNoticesCount();
	
	Notices selectNoticesById(Integer id);
	
	
	Notices selectNoticesByname(String tname);

	List<Notices> checkNoticesByname(String tname);
	
	void deleteNoticesById(Integer id);
	
	void insertNotices(Notices notices);
	
	void updateNotices(Notices notices);
	
}











