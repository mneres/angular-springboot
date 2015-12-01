package be.g00glen00b.service;

import java.util.List;

import be.g00glen00b.model.Goal;

public interface GoalService {
	Goal addGoal(Goal goal, String userEmail);
	
	Goal updateGoal(Goal goal);
	
	void deleteGoal(Integer id);
	
	List<Goal> listAll();
}
