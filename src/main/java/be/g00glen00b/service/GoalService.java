package be.g00glen00b.service;

import java.util.List;

import be.g00glen00b.model.Action;
import be.g00glen00b.model.Goal;
import be.g00glen00b.model.Requirement;

public interface GoalService {
	Goal addGoal(Goal goal, List<Requirement> requirements, List<Action> actions);
	
	Goal updateGoal(Goal goal);
	
	void deleteGoal(Integer id);
	
	List<Goal> listAll();
	
	Goal findOneById(Integer id);
	
	Requirement addRequirementInGoal(Requirement requirement, Goal goal);
}