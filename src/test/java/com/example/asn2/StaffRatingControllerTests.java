package com.example.asn2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StaffRatingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    // Test 1: Homepage should return HTTP 200
    @Test
    void indexShouldReturn200() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    // Test 2: Homepage should contain ratings in the model
    @Test
    void indexShouldContainRatings() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ratings"))
                .andExpect(view().name("index"));
    }
    // Test 3: Valid rating should be created successfully
    @Test
    void createValidRatingShouldRedirect() throws Exception {
        mockMvc.perform(post("/ratings")
                .param("name", "Test Student")
                .param("email", "test@example.com")
                .param("roleType", "TA")
                .param("clarity", "8")
                .param("niceness", "9")
                .param("knowledgeableScore", "7")
                .param("comment", "Testing create"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    // Test 4: Invalid email should return form with errors
    @Test
    void createInvalidEmailShouldReturnForm() throws Exception {
        mockMvc.perform(post("/ratings")
                .param("name", "Test Student")
                .param("email", "invalid-email")
                .param("roleType", "TA")
                .param("clarity", "8")
                .param("niceness", "9")
                .param("knowledgeableScore", "7"))
                .andExpect(status().isOk())
                .andExpect(view().name("form"))
                .andExpect(model().attributeHasFieldErrors("rating", "email"));
    }
}