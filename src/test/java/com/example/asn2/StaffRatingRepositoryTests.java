package com.example.asn2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StaffRatingRepositoryTests {

    @Autowired
    private StaffRatingRepository repository;

    // Create a sample rating for testing
    private StaffRating createSampleRating() {
        StaffRating rating = new StaffRating();

        rating.setName("Test Instructor");
        rating.setEmail("instructor@example.com");
        rating.setRoleType(RoleType.TA);
        rating.setClarity(8);
        rating.setNiceness(9);
        rating.setKnowledgeableScore(7);
        rating.setComment("Good teaching");

        return rating;
    }

    // Test 1: Save a rating to the database
    @Test
    void saveRatingShouldWork() {
        StaffRating rating = createSampleRating();

        StaffRating saved = repository.saveAndFlush(rating);

        assertNotNull(saved.getId());
        assertEquals("Test Instructor", saved.getName());
        assertEquals("instructor@example.com", saved.getEmail());
    }

    // Test 2: Retrieve a saved rating
    @Test
    void findRatingShouldWork() {
        StaffRating rating = createSampleRating();

        StaffRating saved = repository.saveAndFlush(rating);

        StaffRating found = repository.findById(saved.getId())
                .orElse(null);

        assertNotNull(found);
        assertEquals("Test Instructor", found.getName());
        assertEquals(8, found.getClarity());
        assertEquals(9, found.getNiceness());
        assertEquals(7, found.getKnowledgeableScore());
    }

    // Test 3: Delete a rating from the database
    @Test
    void deleteRatingShouldWork() {
        StaffRating rating = createSampleRating();

        StaffRating saved = repository.saveAndFlush(rating);

        Long id = saved.getId();

        repository.deleteById(id);
        repository.flush();

        assertFalse(repository.existsById(id));
    }

    // Test 4: Update an existing rating
    @Test
    void updateRatingShouldWork() {
        StaffRating rating = createSampleRating();

        StaffRating saved = repository.saveAndFlush(rating);

        saved.setClarity(10);
        saved.setComment("Updated comment");

        repository.saveAndFlush(saved);

        StaffRating updated = repository.findById(saved.getId())
                .orElse(null);

        assertNotNull(updated);
        assertEquals(10, updated.getClarity());
        assertEquals("Updated comment", updated.getComment());
    }
}