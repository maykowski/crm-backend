package com.protoslab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/trainings")
@CrossOrigin(origins = "http://localhost:3000")
class TrainingController {

    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingController(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }


    @RequestMapping(method = RequestMethod.GET)
    Collection<Training> getTrainings() {
        return this.trainingRepository.findAll();
    }
}