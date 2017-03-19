package com.protoslab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/{contactId}/followups")
@CrossOrigin(origins = "http://localhost:3000")
class FollowupController {

    private final FollowupRepository followupRepository;

    private final ContactRepository contactRepository;

    @Autowired
    public FollowupController(FollowupRepository followupRepository, ContactRepository contactRepository) {
        this.followupRepository = followupRepository;
        this.contactRepository = contactRepository;
    }


    @RequestMapping(method = RequestMethod.GET)
    Collection<Followup> getFollowups(@PathVariable Integer contactId) {
//		this.validateUser(contactId);
        return this.followupRepository.findByContactId(contactId.longValue(), new PageRequest(0, 1000)).getContent();
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable String contactId, @RequestBody Followup followup) {
//        this.validateUser(contactId);

        Contact contact = this.contactRepository.findOne(new Long(contactId));
        if (contact != null) {
            followup.setContact(contact);
            followup.setCreateDate(new Timestamp(new Date().getTime()));
            followup.setUpdateDate(new Timestamp(new Date().getTime()));
            Followup result = followupRepository.save(followup);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(result.getId()).toUri();

            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.noContent().build();
        }

    }

//    @RequestMapping(method = RequestMethod.GET, value = "/{bookmarkId}")
//    Bookmark readBookmark(@PathVariable String userId, @PathVariable Long bookmarkId) {
//        this.validateUser(userId);
//        return this.followupRepository.findOne(bookmarkId);
//    }
//
//    private void validateUser(String userId) {
//        this.contactRepository.findByUsername(userId).orElseThrow(
//                () -> new UserNotFoundException(userId));
//    }
}