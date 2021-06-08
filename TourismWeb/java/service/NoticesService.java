package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tedu.store.bean.NewsTypes;
import cn.tedu.store.bean.Notices;
import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.NewsTypesMapper;
import cn.tedu.store.mapper.NoticesMapper;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistException;
@Service
public class NoticesService implements INoticesService{
	
	@Resource
	private NoticesMapper mapper;

	public List<Notices> selectAllNotices() {
		// TODO Auto-generated method stub
		return mapper.selectAllNotices();
	}

	public List<Notices> selectNoticesCount() {
		// TODO Auto-generated method stub
		return mapper.selectNoticesCount();
	}

	public Notices selectNoticesById(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectNoticesById(id);
	}

	public Notices selectNoticesByname(String tname) {
		// TODO Auto-generated method stub
		return mapper.selectNoticesByname(tname);
	}

	public List<Notices> checkNoticesByname(String tname) {
		// TODO Auto-generated method stub
		return mapper.checkNoticesByname(tname);
	}

	public void deleteNoticesById(Integer id) {
		// TODO Auto-generated method stub
		mapper.deleteNoticesById(id);
	}

	public void insertNotices(Notices notices) {
		// TODO Auto-generated method stub
		mapper.insertNotices(notices);
	}

	public void updateNotices(Notices notices) {
		// TODO Auto-generated method stub
		mapper.updateNotices(notices);
	}

	
	
	
}

















