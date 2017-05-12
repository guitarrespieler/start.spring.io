package hu.bme.iit.messenger.controller.services;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.bme.iit.messenger.model.User;
import hu.bme.iit.messenger.model.enums.Role;
import hu.bme.iit.messenger.model.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository users;
	
	public static final String EMAIL_PATTERN = 
	        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public List<User> getAllUser(){
		List<User> list = new LinkedList<>();
		
		Iterable<User> temp = users.findAll();
		
		for (User user : temp) {
			list.add(user);
		}
		
		return list;
	}
	
	public User getUser(Long id){
		return users.findOne(id);
	}
	
	public User getUserByEmail(String email){
		return users.findByEmail(email);
	}
	
	public void addUser(User newUser){
		users.save(newUser);
	}
	
	public void updateUser(User user){
		users.save(user);
	}
	
	public void deleteUser(Long id){
		users.delete(id);
	}

	public User loginUser(String email, String password) {
		User user = null;
		try{
			user = users.findByEmail(email);
		}catch (Exception e) {
			return null;
		}
		if(user != null && user.getPassword().equals(password))
			return user;
		return null;
	}

	public void checkParams(User newUser) throws NullPointerException, IllegalArgumentException{
		checkForEmptyFields(newUser);		
		validateFields(newUser);
	}

	private void validateFields(User newUser) throws IllegalArgumentException{
		if(!Pattern.matches(EMAIL_PATTERN, newUser.getEmail())) throw new IllegalArgumentException("Email format is invalid.");
		
		if(getUserByEmail(newUser.getEmail()) != null)	throw new IllegalArgumentException("Email already in use.");
	}

	private void checkForEmptyFields(User newUser) throws NullPointerException{
		if(newUser.getEmail() == null) throw new NullPointerException("Email is empty.");
		
		if(newUser.getBirthDate() == null) throw new NullPointerException("Birth date is empty.");
		
		if(newUser.getCity() == null) throw new NullPointerException("City is empty.");
		
		if(newUser.getFirstName() == null) throw new NullPointerException("First name is empty.");
		
		if(newUser.getLastName() == null) throw new NullPointerException("Last name is empty.");
	}
	

}
