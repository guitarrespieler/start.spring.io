package hu.bme.iit.messenger.model.services;

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
	
	public void addUser(User newUser){
		users.save(newUser);
	}
	
	public void updateUser(User user){
		users.save(user);
	}
	
	public void deleteUser(Long id){
		users.delete(id);
	}
	

}
