package be.g00glen00b.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.g00glen00b.model.Goal;
import be.g00glen00b.model.User;
import be.g00glen00b.repository.GoalRepository;
import be.g00glen00b.repository.UserRepository;

@Service
public class GoalServiceImpl implements GoalService{
	
	private UserRepository userRepository;
	private GoalRepository goalRepository;

	@Autowired
	public GoalServiceImpl(UserRepository userRepository,
		GoalRepository goalRepository) {
	    this.userRepository = userRepository;
	    this.goalRepository = goalRepository;
	}

	@Override
	@Transactional
	public Goal addGoal(Goal goal, String userEmail) {
		User user = userRepository.findOneByEmail(userEmail);
		goal.setUser(user);
		try{
			goalRepository.save(goal);
		}catch(Exception e){
			return new Goal();
		}
		return goal;
	}
	
	@Override
	@Transactional
	public Goal updateGoal(Goal goal) {
		try{
			goalRepository.save(goal);
		}catch(Exception e){
			goal = new Goal();
		}
		return goal;
	}

	@Override
	@Transactional
	public void deleteGoal(Integer id) {
		try{
			goalRepository.delete(id);
		}catch(Exception e){
		}
	}

	@Override
	@Transactional
	public List<Goal> listAll() {
		List<Goal> list = new ArrayList<Goal>();
		try{
			list = goalRepository.findAll();
		}catch(Exception e){
		}
		return list;
	}
}
