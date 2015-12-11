package be.g00glen00b.test.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import be.g00glen00b.model.Goal;
import be.g00glen00b.model.User;
import be.g00glen00b.service.UserService;
import be.g00glen00b.test.AbstractControllerTest;

@Transactional
public class GoalControllerTest extends AbstractControllerTest{

    @Autowired	
    private WebApplicationContext context;
	@Autowired
	private UserService userService;
    
	private MockMvc mockMvc;
	
	//setup goal static variables
	public static final String name = "Go to Ireland";
	public static final String category = "Travel and Trips";
	
	//setup user static variables
	public static final String password = "12345";
	public static final String email = "marcelo.neres1@gmail.com";
	
	@Autowired
	public void SetGoalControllerTest(UserService userService){
		this.userService = userService;
	}
	
    @Before
    public void init() {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        mockMvc =
            MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true).build(); 
    }
	
	public User getUser(){
		//setup user for test 
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		return user;
	}
	public User addUser(User user){
		//save user for test 
		userService.addUser(user);
		return user;
	}
    
	@Test
	public void testGetGoals() throws Exception{
        String uri = "/api/goals";
        
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
        		.accept(MediaType.APPLICATION_JSON)).andReturn();
        
        int status = result.getResponse().getStatus();
        
        Assert.assertEquals(200, status);
	}
	
	/*@Test
	public void testCreateGoal() throws Exception{
        String uri = "/api/goals";
        
        Goal goal = new Goal();
        goal.setName(name);
		goal.setCategory(category);

        String inputJson = super.mapToJson(goal);
        
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
        		.contentType(MediaType.APPLICATION_JSON)
        		.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        
        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        
        Assert.assertEquals(200, status);
	}*/
}
