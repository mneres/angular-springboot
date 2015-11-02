package be.g00glen00b.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.g00glen00b.model.Goal;

public interface GoalRepository extends JpaRepository<Goal, Integer> {

}
