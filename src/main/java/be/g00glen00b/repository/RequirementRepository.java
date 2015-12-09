package be.g00glen00b.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.g00glen00b.model.Requirement;

public interface RequirementRepository extends JpaRepository<Requirement, Integer> {

}
