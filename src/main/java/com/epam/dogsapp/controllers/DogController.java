package com.epam.dogsapp.controllers;

import com.epam.dogsapp.Dog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DogController {
    public List<Dog> dogs = new ArrayList<>();

    @RequestMapping(value = "/dogs", method = RequestMethod.GET)
    public ResponseEntity<String> getAllDogs() {
        return new ResponseEntity<>("iostream", HttpStatus.OK);
    }
}
