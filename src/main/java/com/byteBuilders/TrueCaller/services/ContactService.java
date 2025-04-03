package com.byteBuilders.TrueCaller.services;

import com.byteBuilders.TrueCaller.data.model.Contact;
import com.byteBuilders.TrueCaller.dtos.ContactRequest;
import com.byteBuilders.TrueCaller.dtos.ContactResponse;
import com.byteBuilders.TrueCaller.dtos.NameRequest;

import java.util.List;

public interface ContactService  {
    public ContactResponse addContact(ContactRequest contactRequest);
    public ContactResponse deleteContact(String phoneNumber);
    public List<Contact> getAllContacts();



    Contact findContactByName(NameRequest nameRequest);

    ContactResponse updateContact(ContactRequest contactRequest);

    public Contact findContactByPhoneNumber (String phoneNumber);
    public ContactResponse blockByPhoneNumber (String phoneNumber);
    public ContactResponse unblockByPhoneNumber (String phoneNumber);
    public List<Contact> getBlockedContacts();


//    Contact getContactStatusByPhoneNumber(String phoneNumber);
}
