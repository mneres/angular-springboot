package be.g00glen00b.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.g00glen00b.model.Action;
import be.g00glen00b.model.Goal;
import be.g00glen00b.model.Requirement;
import be.g00glen00b.model.User;
import be.g00glen00b.repository.ActionRepository;
import be.g00glen00b.repository.GoalRepository;
import be.g00glen00b.repository.RequirementRepository;
import be.g00glen00b.repository.UserRepository;

@Service
public class GoalServiceImpl implements GoalService{
	
	private UserRepository userRepository;
	private GoalRepository goalRepository;
	private RequirementRepository requirementRepository;
	private ActionRepository actionRepository;
	
	@Autowired
	public GoalServiceImpl(UserRepository userRepository,GoalRepository goalRepository, 
			RequirementRepository requirementRepository, ActionRepository actionRepository) {
	    this.userRepository = userRepository;
	    this.goalRepository = goalRepository;
	    this.requirementRepository = requirementRepository;
	    this.actionRepository = actionRepository;
	}

	@Override
	@Transactional
	public Goal addGoal(Goal goal, List<Requirement> requirements, List<Action> actions) {
		try{
			User user = userRepository.findOneByEmail(goal.getUser().getEmail());
			if (user != null){
				for(Requirement req : requirements){
					goal.addRequirement(requirementRepository.save(req));
				}
				for(Action act : actions){
					goal.addAction(actionRepository.save(act));
				}
				goalRepository.save(goal);
			}
		}catch(Exception e){
			return new Goal();
		}
		return goal;
	}
	
	@Override
	@Transactional
	public Goal updateGoal(Goal goal, List<Requirement> requirements, List<Action> actions) {	
		/*try{
			for(Requirement req : requirements){
				if(req.getId() == null){
					requirementRepository.save(req);
					goal.addRequirement(req);
				}else{
					
				}
			}
			goalRepository.save(goal);
		}catch(Exception e){
			goal = new Goal();
		}
		return goal;*/
		
		for(Requirement req : requirements){
			if(req.getId() == null){
				requirementRepository.save(req);
				if(req.getId() != null){
					goal.addRequirement(req);
				}else{
					req = new Requirement();
				}
			}else{
				
			}
		}
		goalRepository.save(goal);
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
	@Transactional
	public Goal findOneById(Integer id) {
		Goal goal = new Goal();
		if(id != null){
			try{
				goal = goalRepository.findOneById(id);
			}catch(Exception e){}
		}
		return goal;
	}
	
	@Override
	@Transactional
	public Goal addRequirementInGoal(Requirement requirement, Goal goal){
		if(goal.getId() != null){
			requirementRepository.save(requirement);
			if(requirement.getId() != null){
				goal.addRequirement(requirement);
				goalRepository.save(goal);
			}else{
				requirement = new Requirement();
			}
		}else{
			requirement = new Requirement();
		}
		return goal;
	}
}
