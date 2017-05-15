package hu.bme.iit.messenger.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.bme.iit.messenger.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
