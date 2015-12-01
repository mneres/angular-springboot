package be.g00glen00b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be.g00glen00b.model.Goal;
import be.g00glen00b.model.User;
import be.g00glen00b.service.GoalService;

@RestController
@RequestMapping("/api/goals")
public class GoalController {
	@Autowired
	private GoalService goalService;
  
	@RequestMapping(method = RequestMethod.GET)
	public List<Goal> findItems() {
		return goalService.listAll();
	}
  
	@RequestMapping(method = RequestMethod.POST)
	public Goal addGoal(@RequestBody Goal goal) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		goalService.addGoal(goal, user.getEmail());
		return goal;
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
}
