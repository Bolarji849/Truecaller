package com.byteBuilders.TrueCaller.contollers;

import com.byteBuilders.TrueCaller.data.model.Contact;
import com.byteBuilders.TrueCaller.dtos.ContactRequest;
import com.byteBuilders.TrueCaller.dtos.ContactResponse;
import com.byteBuilders.TrueCaller.dtos.NameRequest;
import com.byteBuilders.TrueCaller.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/contact")
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class ContactController {
    @Autowired
    ContactService contactService;

    @PostMapping("/save")
    public ContactResponse saveContact(@RequestBody ContactRequest contactRequest){
        return  contactService.addContact(contactRequest);
    }
    @DeleteMapping("{num}")
    public ContactResponse deleteContact(@PathVariable("num") String phoneNumber){
          return  contactService.deleteContact(phoneNumber);
    }
    @GetMapping("/contacts")
    public List<Contact> getAllContact(){
        return  contactService.getAllContacts();
    }
    @GetMapping("/name")
    public Contact findContactByName(@RequestBody NameRequest nameRequest){
       return contactService.findContactByName(nameRequest);
    }
    @PostMapping("/update")
     public ContactResponse updateContact(@RequestBody ContactRequest contactRequest) {
        return  contactService.updateContact(contactRequest);
     }
     @PostMapping("{phoneNumber}")
    public Contact SearchContactByPhoneNumber (@PathVariable("phoneNumber") String phoneNumber) {
        return contactService.findContactByPhoneNumber(phoneNumber);
    }
    @PutMapping("{blockNumber}")
    public ContactResponse blockContact(@PathVariable("blockNumber") String phoneNumber){
        return  contactService.blockByPhoneNumber(phoneNumber);
    }
    @PatchMapping("{unblockNumber}")
    public ContactResponse unblockContact (@PathVariable("unblockNumber") String phoneNumber) {
        return  contactService.unblockByPhoneNumber(phoneNumber);
    }



    }
