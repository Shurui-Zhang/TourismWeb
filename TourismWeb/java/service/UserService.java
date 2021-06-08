package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.tedu.store.bean.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameAlreadyExistException;
@Service
public class UserService implements IUserService{
	
	@Resource
	private UserMapper userMapper;

	@Override
	public List<User> login(User user) {
		// TODO Auto-generated method stub
		return userMapper.login(user);
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		userMapper.insertUser(user);
	}

	@Override
	public List<User> selectByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.selectByUsername(username);
	}

	@Override
	public List<User> selectByPhone(String phone) {
		// TODO Auto-generated method stub
		return userMapper.selectByPhone(phone);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userMapper.updateUser(user);
	}

	@Override
	public User selectUserById(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.selectUserById(id);
	}

	@Override
	public List<User> selectAllUsers() {
		// TODO Auto-generated method stub
		return userMapper.selectAllUsers();
	}

	@Override
	public void deleteUsersById(Integer id) {
		// TODO Auto-generated method stub
		userMapper.deleteUsersById(id);
	}
	

	
}

















