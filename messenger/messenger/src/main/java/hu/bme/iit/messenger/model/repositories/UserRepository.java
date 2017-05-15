package hu.bme.iit.messenger.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.bme.iit.messenger.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
	public List<User> findByLastName(String lastName);
	public User findById(Long Id);
}
