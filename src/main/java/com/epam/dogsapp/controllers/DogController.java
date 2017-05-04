package com.epam.dogsapp.controllers;

import com.epam.dogsapp.Dog;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DogController {
    private List<Dog> dogs = new ArrayList<Dog>();

    @PostConstruct
    public void init() {
        for (int i = 0 ; i < 10; i++) {
            dogs.add(new Dog(i, "Name" + i, "Korgi" + i, 50 +i));
        }
    }

    @RequestMapping(value = "/dog", method = RequestMethod.GET)
    public @ResponseBody List<Dog> getAllDogs() {
        return dogs;
    }

    @RequestMapping(value = "/dog/{id}", method = RequestMethod.GET)
    public @ResponseBody Dog getDog(@PathVariable int id) throws Exception {
        Dog dog;
        try {
            dog = dogs.get(id);
        } catch(Exception e) {
            throw new Exception("No dogs with such id");
        }
        return dog;
    }

    @RequestMapping(value = "/dog/{id}", method = RequestMethod.DELETE)
    public Dog freeDog(@PathVariable int id) throws Exception {
        Dog freeDog;
        try {
            freeDog = dogs.get(id);
        } catch(Exception e) {
            throw new Exception("No dogs with such id");
        }
        dogs.remove(id);
        return freeDog;
    }

    @RequestMapping(value = "/dog/{id}", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/dog/{id}", method = RequestMethod.POST)
    public Dog createDog(@PathVariable int id, @RequestParam String name,
                            @RequestParam String breed, @RequestParam Integer weight) {

        Dog dog = new Dog(id, name, breed, weight);
        dogs.add(dog);

        return dog;
    }
}
