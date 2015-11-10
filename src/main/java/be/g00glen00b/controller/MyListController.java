package be.g00glen00b.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be.g00glen00b.model.MyList;
import be.g00glen00b.repository.MyListRepository;

@RestController
@RequestMapping("/mylists")
public class MyListController {
  @Autowired
  private MyListRepository repo;
  
  @RequestMapping(method = RequestMethod.GET)
  public List<MyList> findMyLists() {
    return repo.findAll();
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public MyList addMyList(@RequestBody MyList list) {
    list.setId(null);
    return repo.saveAndFlush(list);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public MyList updateMyList(@RequestBody MyList updatedList, @PathVariable Integer id) {
	updatedList.setId(id);
    return repo.saveAndFlush(updatedList);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteMyList(@PathVariable Integer id) {
    repo.delete(id);
  }
}
