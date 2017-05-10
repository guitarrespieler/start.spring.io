package hu.bme.iit.messenger.model.repositories;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import hu.bme.iit.messenger.model.Conversation;

public interface ConversationRepository extends CrudRepository<Conversation, BigInteger> {

}
