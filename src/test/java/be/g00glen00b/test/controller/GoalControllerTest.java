package be.g00glen00b.test.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import be.g00glen00b.model.Goal;
import be.g00glen00b.model.Requirement;
//import be.g00glen00b.model.Goal;
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
	
	//setup requirement static variables
	public static final String ReqCategory = "Money";
	public static final String ReqValue = "3000 euros";
	public static final String ReqValue2 = "1000 dolares";
	
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
	
	@Test
	public void testCreateGoal() throws Exception{
        //set up URI for goals
		String uri = "/api/goals";
		
		User user = getUser();
		addUser(user);
		
        Goal goal = new Goal();
        goal.setName(name);
		goal.setCategory(category);
		goal.setUser(user);
		
		//Adding requirement for a list in goal controller
		Requirement req = new Requirement();
        req.setCategory(ReqCategory);
        req.setValue(ReqValue);
        String inputJson = super.mapToJson(req);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri + "/addRequirement")
        		.contentType(MediaType.APPLICATION_JSON)
        		.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        
        //Adding a goal with the requirements above
        inputJson = super.mapToJson(goal);
        result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
        		.contentType(MediaType.APPLICATION_JSON)
        		.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        
        //String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        Assert.assertEquals(200, status);
	}
	
	/*@Test
	public void testCreateAndUpdateGoal() throws Exception{
        //set up URI for goals
		String uri = "/api/goals";
		
		User user = getUser();
		addUser(user);
		
        Goal goal = new Goal();
        goal.setName(name);
		goal.setCategory(category);
		goal.setUser(user);

        String inputJson = super.mapToJson(goal);
        
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
        		.contentType(MediaType.APPLICATION_JSON)
        		.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());
        
        Requirement req = new Requirement();
        req.setCategory(ReqCategory);
        req.setValue(ReqValue);
        inputJson = super.mapToJson(req);
        result = mockMvc.perform(MockMvcRequestBuilders.post("/api/goals/2/addRequirement")
        		.contentType(MediaType.APPLICATION_JSON)
        		.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());      
        
        
        result = mockMvc.perform(MockMvcRequestBuilders.delete(uri + "/2")
        		.contentType(MediaType.APPLICATION_JSON)
        		.accept(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        Assert.assertEquals(200, result.getResponse().getStatus());  
	}*/
}
