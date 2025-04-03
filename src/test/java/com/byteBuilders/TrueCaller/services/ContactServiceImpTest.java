package com.byteBuilders.TrueCaller.services;

import com.byteBuilders.TrueCaller.data.model.Contact;
import com.byteBuilders.TrueCaller.data.repository.ContactRepository;
import com.byteBuilders.TrueCaller.dtos.ContactRequest;
import com.byteBuilders.TrueCaller.dtos.ContactResponse;
import com.byteBuilders.TrueCaller.dtos.NameRequest;
import com.byteBuilders.TrueCaller.exception.ContactNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ContactServiceImpTest {
    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    void setUp() {
        contactRepository.deleteAll();
    }

    @Test
    public void testThatContactCanBeSaved() {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setFirstName("ray");
        contactRequest.setLastName("brown");
        contactRequest.setPhoneNumber("09078657854");
        contactService.addContact(contactRequest);
        assertEquals(1, contactRepository.count());
    }

    @Test
    public void testThatContactCanBeDeleted() {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setFirstName("sam");
        contactRequest.setLastName("zek");
        contactRequest.setPhoneNumber("09078657854");
        contactService.addContact(contactRequest);

        System.out.println("Contact saved. Contact count after save: " + contactRepository.count());

        assertEquals(1, contactRepository.count());

        contactService.deleteContact(contactRequest.getPhoneNumber());

        System.out.println("Contact deleted. Contact count after delete: " + contactRepository.count());
        assertEquals(0, contactRepository.count());
    }

    @Test
    public void testThatAllContactCanBeGotten() {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setFirstName("John");
        contactRequest.setLastName("Doe");
        contactRequest.setPhoneNumber("09078657854");
        contactService.addContact(contactRequest);


        ContactRequest contactRequest2 = new ContactRequest();
        contactRequest.setFirstName("gan");
        contactRequest.setLastName("De");
        contactRequest.setPhoneNumber("09018697854");
        contactService.addContact(contactRequest2);
        assertEquals(2, contactRepository.count());

        List<Contact> contacts = contactService.getAllContacts();
        assertEquals(2, contacts.size());
    }

    @Test
    public void testThatContactCanBeFoundByFirstNameOrLastName() {
        ContactRequest contactRequest = new ContactRequest();
        contactRequest.setFirstName("kay");
        contactRequest.setLastName("jess");
        contactRequest.setPhoneNumber("09078657854");
        contactService.addContact(contactRequest);

        NameRequest request = new NameRequest();
        request.setFirstName(contactRequest.getFirstName());
        request.setLastName(contactRequest.getLastName());

        Contact contact = contactService.findContactByName(request);
        assertNotNull(contact);

    }

    @Test
    public void testThatContactCanBeEdited_OrUpdated() {
        ContactRequest initialRequest = new ContactRequest();
        initialRequest.setFirstName("Jane");
        initialRequest.setLastName("sane");
        initialRequest.setPhoneNumber("09078657854");
        contactService.addContact(initialRequest);

        ContactRequest updateRequest = new ContactRequest();
        updateRequest.setPhoneNumber("09078657854");
        updateRequest.setFirstName("New");
        updateRequest.setLastName("Name");

        ContactResponse response = contactService.updateContact(updateRequest);

        assertEquals("Contact updated successfully", response.getMessage());
        assertEquals(1, contactRepository.count());


    }
    @Test
    public void testThatYouCanFindContactByPhoneNumber() {
        ContactRequest initialRequest = new ContactRequest();
        initialRequest.setFirstName("Jane");
        initialRequest.setLastName("sane");
        initialRequest.setPhoneNumber("09078657854");
        contactService.addContact(initialRequest);

       Contact expectedContact = contactService.findContactByPhoneNumber(initialRequest.getPhoneNumber());
       assertEquals("09078657854", expectedContact.getPhoneNumber());

    }
    @Test
    public void testThatYouCanBlockContact() {
        ContactRequest initialRequest = new ContactRequest();
        initialRequest.setFirstName("Jane");
        initialRequest.setLastName("sane");
        initialRequest.setPhoneNumber("09078657854");
        contactService.addContact(initialRequest);

        ContactResponse response = contactService.blockByPhoneNumber(initialRequest.getPhoneNumber());
        Contact contact = contactService.findContactByPhoneNumber(initialRequest.getPhoneNumber());
        assertTrue(contact.isBlocked());
        assertEquals("Contact blocked successfully", response.getMessage());
        assertEquals(1,contactRepository.count());

    }

    @Test
    public void testThatYouCanUnblockContact() {
        ContactRequest initialRequest = new ContactRequest();
        initialRequest.setFirstName("Jane");
        initialRequest.setLastName("Sane");
        initialRequest.setPhoneNumber("09078657854");

        contactService.addContact(initialRequest);
        ContactResponse response = contactService.blockByPhoneNumber(initialRequest.getPhoneNumber());
        Contact contact = contactService.findContactByPhoneNumber(initialRequest.getPhoneNumber());
        assertTrue(contact.isBlocked());
        assertEquals("Contact blocked successfully", response.getMessage());
        assertEquals(1,contactRepository.count());



        Contact blockCheck = contactService.findContactByPhoneNumber(initialRequest.getPhoneNumber());
        assertTrue(blockCheck.isBlocked());

        ContactResponse lResponse = contactService.unblockByPhoneNumber(initialRequest.getPhoneNumber());
        assertEquals("Contact unblocked successfully", lResponse.getMessage());

        Contact unblockCheck = contactService.findContactByPhoneNumber(initialRequest.getPhoneNumber());
        assertFalse(unblockCheck.isBlocked());
    }




}