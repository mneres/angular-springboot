package be.g00glen00b.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.g00glen00b.model.Action;

public interface ActionRepository extends JpaRepository<Action, Integer> {
}
