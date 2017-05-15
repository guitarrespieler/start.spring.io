package hu.bme.iit.messenger.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.bme.iit.messenger.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
