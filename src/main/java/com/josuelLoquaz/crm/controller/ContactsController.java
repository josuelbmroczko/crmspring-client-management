package com.josuelLoquaz.crm.controller;

import com.josuelLoquaz.crm.domain.Contacts;
import com.josuelLoquaz.crm.repository.ContactsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contacts")
public class ContactsController {
    private ContactsRepository contactsRepository;
    public ContactsController(ContactsRepository contactsRepository){
        this.contactsRepository=contactsRepository;
    }

    @PostMapping
    public Contacts addContacts(@RequestBody Contacts contacts){
        return contactsRepository.save(contacts);
    }

    @GetMapping
    public List<Contacts> listContacts(){
        return contactsRepository.findAll();
    }

    @PutMapping("/{id}")
    public void editContacts(@PathVariable("id") UUID id, @RequestBody Contacts contacts){
         contacts.setId(id);
         contactsRepository.save(contacts);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable UUID id){
        contactsRepository.deleteById(id);
    }
    @GetMapping("/{id}")
    public Contacts seachId (@PathVariable UUID id){
        return contactsRepository.findById(id).orElse(null);
    }
    //
    @GetMapping("/account/{accountId}")
    public List<Contacts> getContactsByAccountId(@PathVariable UUID accountId) {
        return contactsRepository.findByAccountsId(accountId);
    }
}
