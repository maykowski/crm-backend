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
@RequestMapping("/statuses")
@CrossOrigin(origins = "http://localhost:3000")
class StatusController {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }


    @RequestMapping(method = RequestMethod.GET)
    Collection<Status> getStatuses() {
        return this.statusRepository.findAll();
    }
}