package com.byteBuilders.TrueCaller.services;

import com.byteBuilders.TrueCaller.data.model.Contact;
import com.byteBuilders.TrueCaller.data.repository.ContactRepository;
import com.byteBuilders.TrueCaller.dtos.ContactRequest;
import com.byteBuilders.TrueCaller.dtos.ContactResponse;
import com.byteBuilders.TrueCaller.dtos.NameRequest;
import com.byteBuilders.TrueCaller.exception.ContactNotFoundException;
import com.byteBuilders.TrueCaller.exception.InvalidCredentialsException;
import com.byteBuilders.TrueCaller.exception.UserAlreadyExist;
import com.byteBuilders.TrueCaller.utills.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImp implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactMapper contactMapper;

    @Override
    public ContactResponse addContact(ContactRequest contactRequest) {
        Optional<Contact> existingContact = contactRepository.findByPhoneNumber(contactRequest.getPhoneNumber());
        if (existingContact.isPresent()) {
            throw new UserAlreadyExist("Contact with this phone number already exists.");
        }
        Contact newContact = contactMapper.mapToContactRequest(contactRequest);
        contactRepository.save(newContact);
        return contactMapper.mapToResponse("Contact added successfully");

    }

    @Override
    public ContactResponse deleteContact(String phoneNumber) {

        Optional<Contact> contactOptional = contactRepository.findByPhoneNumber(phoneNumber);

        if (contactOptional.isPresent()) {
            contactRepository.deleteByPhoneNumber(phoneNumber);
            return contactMapper.mapToResponse("Deleted");
        } else {
            return contactMapper.mapToResponse("Contact not found");
        }
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact findContactByName(NameRequest nameRequest) {
        if (nameRequest == null) {
            throw new IllegalArgumentException("At least one name must be provided");
        }
        return contactRepository.findContactByFirstNameOrLastName(nameRequest.getFirstName(), nameRequest.getLastName())
                .orElseThrow(() -> new ContactNotFoundException(
                        "No contact found with first name '" + nameRequest.getFirstName() +
                                "' or last name '" + nameRequest.getLastName() + "'"
                ));
    }

    @Override
    public ContactResponse updateContact(ContactRequest contactRequest) {
        if(contactRequest.getFirstName() == null || contactRequest.getLastName() == null || contactRequest.getPhoneNumber() == null){
            throw  new InvalidCredentialsException("Can't be empty");
        }
        Optional<Contact> existingContact = contactRepository.findByPhoneNumber(contactRequest.getPhoneNumber());
        if (existingContact.isPresent()){
            Contact updateContact = existingContact.get();
            updateContact.setFirstName(contactRequest.getFirstName());
            updateContact.setLastName(contactRequest.getLastName());
            updateContact.setPhoneNumber(contactRequest.getPhoneNumber());
            contactRepository.save(updateContact);
            return contactMapper.mapToResponse("Contact updated successfully");
        }
        else {
            throw new ContactNotFoundException("Contact not found");
        }
    }

    public Contact findContactByPhoneNumber (String phoneNumber) {
        if (phoneNumber == null) {
            throw new IllegalArgumentException("At least one phone number must be provided");
        }
        return contactRepository.findByPhoneNumber(phoneNumber)
                 .orElseThrow(() -> new ContactNotFoundException("phoneNumber " + phoneNumber + " not found"));

    }

    public ContactResponse blockByPhoneNumber (String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new InvalidCredentialsException("At least one phone number must be provided");
        }
        Contact contact = findContactByPhoneNumber(phoneNumber);

        if (contact.isBlocked()) {
            throw new IllegalStateException("Contact is already blocked");
        }
        else {
            contact.setBlocked(true);
            contactRepository.save(contact);


        }
        return contactMapper.mapToResponse("Contact blocked successfully");
    }

    public ContactResponse unblockByPhoneNumber (String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("At least one phone number must be provided");
        }

       Contact contact = contactRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ContactNotFoundException("phoneNumber " + phoneNumber + " not found"));
        if (!contact.isBlocked()) {
            throw new IllegalStateException("Contact is not blocked");
        }
        contact.setBlocked(false);
        contactRepository.save(contact);
        return contactMapper.mapToResponse("Contact unblocked successfully");
    }
    @Override
    public List<Contact> getBlockedContacts() {
        return contactRepository.findAllByBlockedIsTrue();
    }

}
