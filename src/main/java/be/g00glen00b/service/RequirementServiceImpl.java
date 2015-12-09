package be.g00glen00b.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import be.g00glen00b.model.Requirement;
import be.g00glen00b.repository.RequirementRepository;

public class RequirementServiceImpl implements RequirementService{
	
	private RequirementRepository requirementRepository;

	@Autowired
	public RequirementServiceImpl(RequirementRepository requirementRepository) {
	    this.requirementRepository = requirementRepository;
	}

	@Override
	@Transactional
	public Requirement addRequirement(Requirement req) {
		try{
			requirementRepository.save(req);
		}catch(Exception e){
			return new Requirement();
		}
		return req;
	}

	@Override
	public Requirement updateRequirement(Requirement req) {
		try{
			requirementRepository.save(req);
		}catch(Exception e){
			return new Requirement();
		}
		return req;
	}

	@Override
	public boolean deleteRequirement(Integer id) {
		try{
			requirementRepository.delete(id);
		}catch(Exception e){
			return false;
		}
		return true;
	}

}
