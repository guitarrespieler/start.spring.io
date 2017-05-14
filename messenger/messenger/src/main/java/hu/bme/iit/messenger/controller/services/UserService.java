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
	
	public enum ContactState {FRIENDS, STRANGERS, SAME_USER};
	
	@Autowired
	private UserRepository users;
	
	public static final String EMAIL_PATTERN = 
	        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	/**
	 * @return true if they are friends
	 */
	public ContactState areFriends(User user1, User user2){
		if(user1.equals(user2))
			return ContactState.SAME_USER;
		
		List<User> friendslist = user1.getFriends();
		
		for (User user : friendslist) {
			if(user2.equals(user))
				return ContactState.FRIENDS;
		}
		return ContactState.STRANGERS;
	}
	
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

	public User loginUser(String email, String password) throws IllegalArgumentException{
		User user = null;
		user = getUserByEmail(email);

		if(user != null && user.getPassword().equals(password))
			return user;
		
		throw new IllegalArgumentException("Email or password is incorrect.");
	}

	public void checkParams(User newUser) throws NullPointerException, IllegalArgumentException{
		checkEmailFormat(newUser.getEmail());
		checkForEmptyFields(newUser);		
	}

	public void checkEmailFormat(String email) throws NullPointerException, IllegalArgumentException{
		if(email == null) throw new NullPointerException("Email is empty.");
		
		if(!Pattern.matches(EMAIL_PATTERN, email)) throw new IllegalArgumentException("Email format is invalid.");		
	}

	private void checkForEmptyFields(User newUser) throws NullPointerException{	
		if(newUser.getBirthDate() == null) throw new NullPointerException("Birth date is empty.");
		
		if(newUser.getCity() == null) throw new NullPointerException("City is empty.");
		
		if(newUser.getFirstName() == null) throw new NullPointerException("First name is empty.");
		
		if(newUser.getLastName() == null) throw new NullPointerException("Last name is empty.");
	}
	

}
