package hu.bme.iit.messenger.model.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import hu.bme.iit.messenger.model.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

}
