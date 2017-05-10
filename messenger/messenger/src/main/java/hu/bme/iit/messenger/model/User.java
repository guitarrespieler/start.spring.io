package hu.bme.iit.messenger.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import hu.bme.iit.messenger.model.enums.Role;


/**
 * Represents a user. DTO class.
 * @author zsigatibor
 *
 */
@Entity(name = "USERS")
public class User {
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE)
	@Column(nullable=false, unique=true)
	private Long id;
	
	@Column(nullable=false)
	private String firstName;
	
	@Column(nullable=false)
	private String lastName;

	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String placeOfBirth;
	
	@Column(nullable=false)
	private Date birthDate;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<User> friends;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Conversation> conversations;

	
	public User(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
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
	
	@Override
	public String toString() {
		return "id:" + this.getId() + " - " + this.getFirstName() + this.getLastName();
	}
	
}
