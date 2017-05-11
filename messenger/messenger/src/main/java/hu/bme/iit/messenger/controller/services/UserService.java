package hu.bme.iit.messenger.controller.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.bme.iit.messenger.model.User;
import hu.bme.iit.messenger.model.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository users;
	
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
	

}
