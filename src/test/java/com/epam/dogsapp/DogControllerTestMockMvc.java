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
    public void testPostDog() throws Exception {
        Dog dog = new Dog("Shrella", "Husky", 13);
        this.mvc.perform(post("/dog").content(convertObjectToJson(dog)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(model().size(1));
    }

    public String convertObjectToJson(Dog obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}