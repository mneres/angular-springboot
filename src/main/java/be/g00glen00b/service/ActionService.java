package be.g00glen00b.service;

import be.g00glen00b.model.Action;

public interface ActionService {
	Action addAction(Action action);
	
	Action updateAction(Action action);
	
	boolean deleteAction(Integer id);
}
