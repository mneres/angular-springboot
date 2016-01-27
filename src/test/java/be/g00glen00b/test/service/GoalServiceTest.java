package be.g00glen00b.test.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import be.g00glen00b.Application;
import be.g00glen00b.model.Goal;
import be.g00glen00b.model.Requirement;
import be.g00glen00b.model.User;
import be.g00glen00b.service.GoalService;
import be.g00glen00b.service.UserService;

/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TransactionConfiguration
@Transactional*/
public class GoalServiceTest {
	
	//setup goal static variables
	public static final String name = "Go to Ireland";
	public static final String category = "Travel and Trips";
	
	//setup requirement static variables
	public static final String ReqCategory = "Money";
	public static final String ReqValue = "3000 euros";
	public static final String ReqValue2 = "1000 reais";
	
	//setup user static variables
	public static final String password = "12345";
	public static final String email = "marcelo.neres1@gmail.com";
	
	@Autowired
	private GoalService goalService;
	@Autowired
	private UserService userService;
	
	@Autowired
	public void SetGoalService(GoalService goalService, UserService userService){
		this.goalService = goalService;
		this.userService = userService;
	}
	
	public User getUser(){
		//setup user for test 
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		return user;
	}
	public User addUser(User user){
		//save user for test 
		userService.addUser(user);
		return user;
	}

	
	/*public Goal addGoal(Goal goal, List<Requirement> requirements){
		//setup and save goal for test 
		goalService.addGoal(goal, requirements);
		return goal;
	}
	
	@Test
	@Rollback(true)
	public void addGoalWithRequeriment(){	
		List<Requirement> requirements = new ArrayList<Requirement>();

		User user = getUser();
		addUser(user);
		
		Requirement req = new Requirement();
		req.setCategory(ReqCategory);
		req.setValue(ReqValue);
		
		Goal goal = new Goal();
		goal.setName(name);
		goal.setCategory(category);
		requirements.add(req);
		
		addGoal(goal, requirements);
		assertNull(goal.getId());
		
		goal.setUser(user);
		
		addGoal(goal, requirements);
		assertNotNull(goal.getId());
	}
	
	@Test
	@Rollback(true)
	public void FindAndDeleteGoal(){
		List<Requirement> requirements = new ArrayList<Requirement>();
		User user = getUser();
		addUser(user);
		Goal goal = new Goal();
		goal.setName(name);
		goal.setCategory(category);
		goal.setUser(user);
		addGoal(goal, requirements);
		
		Goal goalTest = new Goal();
		goalTest = goalService.findOneById(goal.getId());
		assertNotNull(goalTest.getId());
		
		goalService.deleteGoal(goalTest.getId());
		
		goalTest = goalService.findOneById(goal.getId());
		assertNull(goalTest);
	}*/
}