package com.epam.dogsapp.controllers;

import com.epam.dogsapp.Dog;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class DogController {
    private Map<Integer, Dog> dogs = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        for (int i = 0 ; i < 10; i++) {
            dogs.put(i, (new Dog(i, "Name" + i, "Korgi" + i, 50 + i)));
        }
    }

    @RequestMapping(value = "/dog", method = RequestMethod.GET)
    public @ResponseBody
    Map getAllDogs() {
        return dogs;
    }

    @RequestMapping(value = "/dog/{id}", method = RequestMethod.GET)
    public ResponseEntity<Dog> getDog(@PathVariable int id) throws Exception {
        Dog dog = dogs.get(id);
        if (dog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dog, HttpStatus.OK);
    }

    @RequestMapping(value = "/dog/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Dog> freeDog(@PathVariable int id) throws Exception {
        Dog freeDog = dogs.remove(id);
        if (freeDog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(freeDog, HttpStatus.OK);
    }

    @RequestMapping(value = "/dog/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Dog> updateDog(@PathVariable int id, @Valid @RequestParam(required = false) String name,
                            @RequestParam(required = false) String breed, @Valid @RequestParam(required = false) Integer weight) throws Exception {
        Dog dog;
        try {
            dog = dogs.get(id);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (name != null) {
            dog.setName(name);
        }

        if (breed != null) {
            dog.setBreed(breed);
        }

        if (weight != null) {
            dog.setWeight(weight);
        }

        return new ResponseEntity<>(dog, HttpStatus.OK);
    }

    @RequestMapping(value = "/dog", method = RequestMethod.POST)
    public ResponseEntity<Dog> createDog(@Valid @RequestBody Dog dog) {
        Integer id = 0;
        for (Integer key : dogs.keySet()) {
            if (id < key) {
                id = key;
            }
        }
        dog.setId(id + 1);
        dogs.put(id + 1, dog);

        HttpHeaders header = new HttpHeaders();
        header.set("Id", String.valueOf(dog.getId()));

        return new ResponseEntity<>(dog, header, HttpStatus.OK);
    }
}
