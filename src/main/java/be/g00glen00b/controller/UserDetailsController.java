package be.g00glen00b.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetailsController extends BaseController{
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
}
