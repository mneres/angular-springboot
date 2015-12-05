package be.g00glen00b.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.g00glen00b.model.User;
import be.g00glen00b.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
	    this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public User addUser(User user) {
		try{
			userRepository.save(user);
		}catch(Exception e){
			return new User();
		}
		return user;
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		try{
			userRepository.save(user);
		}catch(Exception e){
			return new User();
		}
		return user;
	}

	@Override
	@Transactional
	public void deleteUser(Integer id) {
		try{
			userRepository.delete(id);
		}catch(Exception e){
		}
	}

	@Override
	@Transactional
	public List<User> listAll() {
		List<User> list = new ArrayList<User>();
		try{
			list = userRepository.findAll();
		}catch(Exception e){
		}
		return list;
	}

	@Override
	public User findOneByEmail(String email) {
		User user = new User();
		try{
			user = userRepository.findOneByEmail(email);
		}catch(Exception e){
		}
		return user;
	}

}
