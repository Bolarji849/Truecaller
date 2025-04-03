package com.byteBuilders.TrueCaller.data.repository;

import com.byteBuilders.TrueCaller.data.model.Contact;
import com.byteBuilders.TrueCaller.dtos.ContactResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends MongoRepository<Contact, String> {
    Optional <Contact> findByPhoneNumber(String phoneNumber);

    Optional <Contact> findContactByFirstNameOrLastName(String firstName, String lastName);

    List<Contact> findAllByBlockedIsTrue();
    Optional<Contact> findContactByBlockedIsTrue(String phoneNumber);

    void deleteByPhoneNumber(String phoneNumber);
}
