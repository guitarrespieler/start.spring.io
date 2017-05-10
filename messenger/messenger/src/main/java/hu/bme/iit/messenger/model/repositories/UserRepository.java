package hu.bme.iit.messenger.model.repositories;

import javax.annotation.ManagedBean;

import org.springframework.data.repository.CrudRepository;

import hu.bme.iit.messenger.model.User;

@ManagedBean
public interface UserRepository extends CrudRepository<User, Long> {
	public User findByEmail(String email);
}
