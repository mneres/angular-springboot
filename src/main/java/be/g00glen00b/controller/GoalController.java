package be.g00glen00b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be.g00glen00b.model.Goal;
import be.g00glen00b.repository.GoalRepository;

@RestController
@RequestMapping("/api/goals")
public class GoalController {
  @Autowired
  private GoalRepository repo;
  
  @RequestMapping(method = RequestMethod.GET)
  public List<Goal> findItems() {
    return repo.findAll();
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public Goal addGoal(@RequestBody Goal goal) {
    goal.setId(null);
    return repo.saveAndFlush(goal);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public Goal updateGoal(@RequestBody Goal updatedGoal, @PathVariable Integer id) {
	updatedGoal.setId(id);
    return repo.saveAndFlush(updatedGoal);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteGoal(@PathVariable Integer id) {
    repo.delete(id);
  }
}
