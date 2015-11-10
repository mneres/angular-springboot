package be.g00glen00b.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.g00glen00b.model.MyList;

public interface MyListRepository extends JpaRepository<MyList, Integer> {

}
