package hu.bme.iit.messenger.model.repositories;

import org.springframework.data.repository.CrudRepository;

import hu.bme.iit.messenger.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	public User findByEmail(String email);
}
