package be.g00glen00b.test.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import be.g00glen00b.Application;
import be.g00glen00b.model.User;
import be.g00glen00b.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TransactionConfiguration
@Transactional
public class UserServiceTest {
	//setup static variables
	public static final String password = "12345";
	public static final String email = "marcelo.neres1@gmail.com";
	public static final String updatepassword = "123";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	public void SetUserService(UserService userService){
		this.userService = userService;
	}
	
	@Test
	@Rollback(true)
	public void addAndUpdateUser(){
		//setup user
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		
		//save user and verify ID user after save
		userService.addUser(user);
		assertNotNull(user.getId());
		
		//update user
		User updateUser = userService.findOneByEmail(email);
		updateUser.setPassword(updatepassword);
		userService.updateUser(updateUser);
		
		//get user updated and test findOneByEmail method
		User updatedUser = userService.findOneByEmail(email);
		assertTrue(updatedUser.getEmail() == email);
		
		//test if the password has been updated
		assertTrue(password != updatedUser.getPassword());
	}
	
	@Test
	@Rollback(true)
	public void addAndDeleteUser(){
		//setup user
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		
		//save user and test
		userService.addUser(user);
		assertNotNull(user.getId());
		
		//get user before deleting
		User beforeDelete = userService.findOneByEmail(email);
		
		//delete user
		userService.deleteUser(user.getId());
		
		//get user deleted
		User afterDelete = userService.findOneByEmail(email);
		
		//test if the password has been updated
		assertTrue(!beforeDelete.equals(afterDelete));
	}
	
	/*@Test
	@Rollback(true)
	public void listUser(){
		Integer count = 0;
		Integer quantityBefore = userService.listAll().size();
		Collection<User> lst = userService.listAll(); 
		for(@SuppressWarnings("unused") User u : lst){
			count++;
		}
		assertTrue(count == quantityBefore);
	}*/
}
