package com.epam.dogsapp.controllers;

import com.epam.dogsapp.Dog;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
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
    public @ResponseBody Dog getDog(@PathVariable int id) throws Exception {
        return dogs.get(id);
    }

    @RequestMapping(value = "/dog/{id}", method = RequestMethod.DELETE)
    public Dog freeDog(@PathVariable int id) throws Exception {
        Dog freeDog;
        freeDog = dogs.get(id);
        dogs.remove(id);
        return freeDog;
    }

    @RequestMapping(value = "/dog/{id}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public Dog updateDog(@PathVariable int id, @RequestParam(required = false) String name,
                            @RequestParam(required = false) String breed, @RequestParam(required = false) Integer weight) throws Exception {
        Dog dog;
        try {
            dog = dogs.get(id);
        } catch(Exception e) {
            throw new Exception("No dogs with such id");
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

        return dog;
    }

    @RequestMapping(value = "/dog", method = RequestMethod.POST,  produces = "application/json;charset=UTF-8")
    public Dog createDog(@RequestBody Dog dog) {
        Integer id = 0;
        for (Integer key : dogs.keySet()) {
            if (id < key) {
                id = key;
            }
        }
        dog.setId(id + 1);
        dogs.put(id + 1, dog);

        return dog;
    }
}
