package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.bean.User;

public interface UserMapper {
	
	List<User> login(User user);

	void insertUser(User user);
	
	List<User> selectByUsername(String username);

	List<User> selectByPhone(String phone);
	
	void updateUser(User user);
	
	User selectUserById(Integer id);
	
	List<User> selectAllUsers();
	
	void deleteUsersById(Integer id);

}






















