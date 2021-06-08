package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.Admins;
import cn.tedu.store.bean.User;

public interface IAdminsService {

	List<Admins> login(Admins admins);
	
	List<Admins> selectAllAdminss();

	void insertAdmins(Admins admins);
	
	List<Admins> selectByAdminsname(String aname);

	List<Admins> selectAdminsByPhone(String phone);
	
	void updateAdmins(Admins admins);
	
	Admins selectAdminsById(Integer id);
	
	void deleteAdminssById(Integer id);
	
}











