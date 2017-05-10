package hu.bme.iit.messenger.model;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository{

    List<User> findByLastName(String lastName);
    User findById(long Id);
}
