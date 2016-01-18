package be.g00glen00b.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be.g00glen00b.model.Action;
import be.g00glen00b.model.Goal;
import be.g00glen00b.model.Requirement;
import be.g00glen00b.model.User;
import be.g00glen00b.service.GoalService;

@RestController
@RequestMapping("/api/goals")
public class GoalController extends BaseController{
	@Autowired
	private GoalService goalService;
	private List<Requirement> requirements;
	private List<Action> actions;
	
	@Autowired
	public GoalController(GoalService goalService){
		this.goalService = goalService;
		requirements = new ArrayList<Requirement>();
		actions = new ArrayList<Action>();
	}
  
	@RequestMapping(method = RequestMethod.GET)
	public List<Goal> findItems() {
		return goalService.listAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Goal addGoal(@RequestBody Goal goal) {
		if(goal.getUser() == null || goal.getUser().getId() == null){
			//get user authenticated on the system and set in the goal
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = (User)authentication.getPrincipal();
			goal.setUser(user);
		}
		//add new goal
		goalService.addGoal(goal, requirements, actions);
		return goal;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Goal getGoal(@PathVariable Integer id) {
		return goalService.findOneById(id);
	}
  
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Goal updateGoal(@RequestBody Goal updatedGoal, @PathVariable Integer id) {
		updatedGoal.setId(id);
		return goalService.updateGoal(updatedGoal);
	}
  
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteGoal(@PathVariable Integer id) {
		goalService.deleteGoal(id);
	}
	
	@RequestMapping(value = "/addRequirements", method = RequestMethod.POST)
	public List<Requirement> addRequirements(@RequestBody List<Requirement> reqs){
		this.requirements = reqs;
		return this.requirements;
	}
	
	@RequestMapping(value = "/addActions", method = RequestMethod.POST)
	public List<Action> addActions(@RequestBody List<Action> acts){
		this.actions = acts;
		return this.actions;
	}
	
	
	/*
	 * This code needs to be revised
	 */
	@RequestMapping(value = "/addRequirement", method = RequestMethod.POST)
	public Requirement addRequirement(@RequestBody Requirement requirement){
		this.requirements.add(requirement);
		return requirement;	
	}
	
	@RequestMapping(value = "/{idGoal}/addRequirement", method = RequestMethod.POST)
	public Requirement addRequirementForGoal(@PathVariable Integer idGoal, @RequestBody Requirement requirement){
		Goal goal = goalService.findOneById(idGoal);
		return goalService.addRequirementInGoal(requirement, goal);	
	}
	/*
	 * Until here
	 */
}
