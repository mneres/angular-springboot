package be.g00glen00b.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import be.g00glen00b.model.Action;
import be.g00glen00b.repository.ActionRepository;

public class ActionServiceImpl implements ActionService{
	
	private ActionRepository actionRepository;

	@Autowired
	public ActionServiceImpl(ActionRepository actionRepository) {
	    this.actionRepository = actionRepository;
	}

	@Override
	@Transactional
	public Action addAction(Action act) {
		try{
			actionRepository.save(act);
		}catch(Exception e){
			return new Action();
		}
		return act;
	}

	@Override
	public Action updateAction(Action act) {
		try{
			actionRepository.save(act);
		}catch(Exception e){
			return new Action();
		}
		return act;
	}

	@Override
	public boolean deleteAction(Integer id) {
		try{
			actionRepository.delete(id);
		}catch(Exception e){
			return false;
		}
		return true;
	}

}
