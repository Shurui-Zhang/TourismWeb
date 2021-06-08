package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tedu.store.bean.Admins;
import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.AdminsMapper;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistException;
@Service
public class AdminsService implements IAdminsService{
	
	@Resource
	private AdminsMapper adminsMapper;

	@Override
	public List<Admins> login(Admins admins) {
		// TODO Auto-generated method stub
		return adminsMapper.login(admins);
	}

	@Override
	public List<Admins> selectAllAdminss() {
		// TODO Auto-generated method stub
		return adminsMapper.selectAllAdminss();
	}

	@Override
	public void insertAdmins(Admins admins) {
		// TODO Auto-generated method stub
		adminsMapper.insertAdmins(admins);
	}

	@Override
	public List<Admins> selectByAdminsname(String aname) {
		// TODO Auto-generated method stub
		return adminsMapper.selectByAdminsname(aname);
	}

	@Override
	public List<Admins> selectAdminsByPhone(String phone) {
		// TODO Auto-generated method stub
		return adminsMapper.selectAdminsByPhone(phone);
	}

	@Override
	public void updateAdmins(Admins admins) {
		// TODO Auto-generated method stub
		adminsMapper.updateAdmins(admins);
	}

	@Override
	public Admins selectAdminsById(Integer id) {
		// TODO Auto-generated method stub
		return adminsMapper.selectAdminsById(id);
	}

	@Override
	public void deleteAdminssById(Integer id) {
		// TODO Auto-generated method stub
		adminsMapper.deleteAdminssById(id);
	}

	
	

	
}

















