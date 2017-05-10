package hu.bme.iit.messenger.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.NaturalId;

import hu.bme.iit.messenger.model.enums.Role;


/**
 * Represents a user. DTO class.
 * @author zsigatibor
 *
 */
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String firstName;
	
	private String lastName;

	private String email;
	
	private String password;
	
	private String city;
	
	private Date birthDate;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@ManyToMany
	private List<User> friends;
	
	@ManyToMany
	private List<Conversation> conversations;

	
	public User(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public List<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "id:" + this.getId() + " - " + this.getFirstName() + this.getLastName();
	}
}
