package com.byteBuilders.TrueCaller.utills;

import com.byteBuilders.TrueCaller.data.model.Contact;
import com.byteBuilders.TrueCaller.dtos.ContactRequest;
import com.byteBuilders.TrueCaller.dtos.ContactResponse;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {
    public Contact mapToContactRequest(ContactRequest contactRequest) {
        Contact contact = new Contact();
        contact.setFirstName(contactRequest.getFirstName());
        contact.setLastName(contactRequest.getLastName());
        contact.setPhoneNumber(contactRequest.getPhoneNumber());
        return contact;
    }

    public ContactResponse mapToResponse(String response) {
        ContactResponse contactResponse = new ContactResponse();
        contactResponse.setMessage(response);
        return contactResponse;
    }
    public Contact mapToExistingContact(ContactRequest request, Contact existingContact) {

        if (request.getFirstName() != null) {
            existingContact.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            existingContact.setLastName(request.getLastName());
        }
        return existingContact;
    }
//    public void updateContactFromRequest(ContactRequest request, Contact contact) {
//        contact.setFirstName(request.getFirstName());
//        contact.setLastName(request.getLastName());
//        contact.setPhoneNumber(request.getPhoneNumber());
//
//    }




}
