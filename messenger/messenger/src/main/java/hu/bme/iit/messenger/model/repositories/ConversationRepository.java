package hu.bme.iit.messenger.model.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.bme.iit.messenger.model.Conversation;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

}
