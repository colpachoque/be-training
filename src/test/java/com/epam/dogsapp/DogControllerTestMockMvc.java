package com.epam.dogsapp;

import com.epam.dogsapp.controllers.DogController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dispatcher-servlet.xml")
public class DogControllerTestMockMvc {

    private MockMvc mvc;
    private DogController dogCtrl = new DogController();

    @BeforeTest
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders
                .standaloneSetup(dogCtrl)
                .build();
    }

    @Test
    public void testGetDog() throws Exception {
        this.mvc.perform(get("/dog"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void shouldCreateDog() throws Exception {
        Dog dog = new Dog("Shrella", "Husky", 13);
        Integer id = postDog(dog);
        getDogById(id);
    }

    @Test
    public void shouldUpdateDog() throws Exception {
        Dog dog = new Dog("Shrella", "Husky", 7);
        Integer id = postDog(dog);
        dog.setWeight(11);
        MvcResult updatedDog = putDog(dog, id);
        this.mvc.perform(put("/dog/" + id).contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(dog)))
                .andExpect(status().isOk());
//                .andExpect(content().json(updatedDog.mockResponse.content));
    }

    @Test
    public void shouldDeleteDog() throws Exception {
        Dog dog = new Dog("Shrella", "Husky", 7);
        Integer id = postDog(dog);
        this.mvc.perform(delete("/dog/" + id))
                .andExpect(status().isOk());
        this.mvc.perform(get("/dog/" + id))
                .andExpect(status().isNotFound());

    }

    @Test
    public void shouldFailCreateDogWithShortName() throws Exception {
        Dog dog = new Dog("S", "Husky", 7);
        mvc.perform(post("/dog")
                .content(convertObjectToJson(dog))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }



    public Integer postDog(Dog dog) throws Exception {
        MvcResult res = this.mvc.perform(post("/dog").contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(dog)))
                .andExpect(status().isOk())
                .andReturn();
        String headerId = res.getResponse().getHeader("Id");
        return Integer.parseInt(headerId);
    }

    public MvcResult putDog(Dog dog, Integer id) throws Exception {
        MvcResult res = this.mvc.perform(put("/dog/" + id)
                .param("weight", String.valueOf(dog.getWeight())))
                .andExpect(status().isOk())
                .andReturn();

        return res;
    }

    public MvcResult getDogById(Integer id) throws Exception {
        MvcResult res = this.mvc.perform(get("/dog/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();
        return res;
    }

    public String convertObjectToJson(Dog obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}