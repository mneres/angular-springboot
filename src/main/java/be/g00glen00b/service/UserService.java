package be.g00glen00b.service;

import java.util.List;

import be.g00glen00b.model.User;

public interface UserService {
	User addUser(User user);
	
	User updateUser(User user);
	
	void deleteUser(Integer id);
	
	List<User> listAll();
	
	User findOneByEmail(String email);
}
