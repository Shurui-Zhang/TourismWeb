package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.bean.User;

public interface IUserService {

	List<User> login(User user);

	void insertUser(User user);
	
	List<User> selectByUsername(String username);

	List<User> selectByPhone(String phone);
	
	void updateUser(User user);
	
	User selectUserById(Integer id);
	
	List<User> selectAllUsers();
	
	void deleteUsersById(Integer id);
	
}











