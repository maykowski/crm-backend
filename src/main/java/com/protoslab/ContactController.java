package com.protoslab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Wojtek on 09.03.2017.
 */

@RestController
@Component
@Service
@CrossOrigin(origins = "http://localhost:3000")
public class ContactController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    ContactRepository contactRepository;

//    @RequestMapping("/greeting")
//    public Contact greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Contact(counter.incrementAndGet(),
//                String.format(template, name));
//    }

    @RequestMapping("/contacts")

    public List<Contact> getAllContacts() {
        return contactRepository.findAll(new PageRequest(1, 10)).getContent();
//        return "hello";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contacts/{contactId}")
    Contact getContact(@PathVariable Integer contactId) {
//		this.validateUser(contactId);
        return this.contactRepository.findOne(contactId.longValue());
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/contacts/{contactId}")
    ResponseEntity<?> update(@RequestBody Contact contact) {
//        this.validateUser(contactId);

//        Contact contact = this.contactRepository.findOne(new Long(contactId));
        Contact result = contactRepository.save(contact);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
}
