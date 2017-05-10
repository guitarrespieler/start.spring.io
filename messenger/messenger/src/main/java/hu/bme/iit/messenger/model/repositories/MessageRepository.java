package hu.bme.iit.messenger.model.repositories;

import java.math.BigInteger;

import javax.annotation.ManagedBean;

import org.springframework.data.repository.CrudRepository;

import hu.bme.iit.messenger.model.Message;

@ManagedBean
public interface MessageRepository extends CrudRepository<Message, BigInteger> {

}
