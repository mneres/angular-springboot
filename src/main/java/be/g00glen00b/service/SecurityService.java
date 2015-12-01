package be.g00glen00b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import be.g00glen00b.model.User;
import be.g00glen00b.repository.UserRepository;

@Service
public class SecurityService  implements UserDetailsService{
	@Autowired
	private UserRepository repo;
	
	@Override	
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = repo.findOneByEmail(userName);
		if(user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
		return user;
	}
}
	