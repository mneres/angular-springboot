package be.g00glen00b.service;

import be.g00glen00b.model.Requirement;

public interface RequirementService {
	Requirement addRequirement(Requirement req);
	
	Requirement updateRequirement(Requirement req);
	
	boolean deleteRequirement(Integer id);
}
