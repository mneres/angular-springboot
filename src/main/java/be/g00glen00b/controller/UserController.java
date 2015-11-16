package be.g00glen00b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be.g00glen00b.model.User;
import be.g00glen00b.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  private UserRepository repo;
  
  @RequestMapping(method = RequestMethod.GET)
  public List<User> findItems() {
    return repo.findAll();
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public User addUser(@RequestBody User user) {
    user.setId(null);
    return repo.saveAndFlush(user);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public User updateUser(@RequestBody User updatedUser, @PathVariable Integer id) {
	updatedUser.setId(id);
    return repo.saveAndFlush(updatedUser);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteUser(@PathVariable Integer id) {
    repo.delete(id);
  }
}
