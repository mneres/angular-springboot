package be.g00glen00b.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.g00glen00b.model.Goal;
import be.g00glen00b.model.Requirement;
import be.g00glen00b.model.User;
import be.g00glen00b.repository.GoalRepository;
import be.g00glen00b.repository.RequirementRepository;
import be.g00glen00b.repository.UserRepository;

@Service
public class GoalServiceImpl implements GoalService{
	
	private UserRepository userRepository;
	private GoalRepository goalRepository;
	private RequirementRepository requirementRepository;
	
	@Autowired
	public GoalServiceImpl(UserRepository userRepository,
		GoalRepository goalRepository, RequirementRepository requirementRepository) {
	    this.userRepository = userRepository;
	    this.goalRepository = goalRepository;
	    this.requirementRepository = requirementRepository;
	}

	@Override
	@Transactional
	public Goal addGoal(Goal goal, List<Requirement> requirements) {
		try{
			User user = userRepository.findOneByEmail(goal.getUser().getEmail());
			goal.setUser(user);
			
			for(Requirement req : requirements){
				goal.addRequirement(requirementRepository.save(req));
			}
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

	@Override
	public Goal findOneById(Integer id) {
		Goal goal = new Goal();
		if(id != null){
			try{
				goal = goalRepository.findOneById(id);
			}catch(Exception e){}
		}
		return goal;
	}
}
