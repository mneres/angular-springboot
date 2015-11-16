package be.g00glen00b.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.g00glen00b.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findOneByEmail(String email);
}
