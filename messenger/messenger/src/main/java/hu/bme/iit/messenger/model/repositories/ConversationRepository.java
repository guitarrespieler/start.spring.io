package hu.bme.iit.messenger.model.repositories;

import java.math.BigInteger;

import javax.annotation.ManagedBean;

import org.springframework.data.repository.CrudRepository;

import hu.bme.iit.messenger.model.Conversation;

@ManagedBean
public interface ConversationRepository extends CrudRepository<Conversation, BigInteger> {

}
