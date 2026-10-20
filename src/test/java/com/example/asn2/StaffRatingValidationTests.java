package com.example.asn2;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StaffRatingValidationTests {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    static void tearDown() {
        factory.close();
    }

    private StaffRating createValidRating() {
        StaffRating rating = new StaffRating();

        rating.setName("Test Professor");
        rating.setEmail("test@example.com");
        rating.setRoleType(RoleType.PROF);
        rating.setClarity(8);
        rating.setNiceness(9);
        rating.setKnowledgeableScore(7);
        rating.setComment("Good teaching.");

        return rating;
    }

    @Test
    void validRatingShouldPass() {
        StaffRating rating = createValidRating();

        Set<ConstraintViolation<StaffRating>> violations =
                validator.validate(rating);

        assertTrue(violations.isEmpty());
    }

    @Test
    void invalidEmailShouldBeRejected() {
        StaffRating rating = createValidRating();

        rating.setEmail("invalid-email");

        Set<ConstraintViolation<StaffRating>> violations =
                validator.validate(rating);

        assertFalse(violations.isEmpty());
    }

    @Test
    void emptyNameShouldBeRejected() {
        StaffRating rating = createValidRating();

        rating.setName("");

        Set<ConstraintViolation<StaffRating>> violations =
                validator.validate(rating);

        assertFalse(violations.isEmpty());
    }

    @Test
    void clarityAboveTenShouldBeRejected() {
        StaffRating rating = createValidRating();

        rating.setClarity(11);

        Set<ConstraintViolation<StaffRating>> violations =
                validator.validate(rating);

        assertFalse(violations.isEmpty());
    }

    @Test
    void nicenessBelowOneShouldBeRejected() {
        StaffRating rating = createValidRating();

        rating.setNiceness(0);

        Set<ConstraintViolation<StaffRating>> violations =
                validator.validate(rating);

        assertFalse(violations.isEmpty());
    }

    @Test
    void knowledgeAboveTenShouldBeRejected() {
        StaffRating rating = createValidRating();

        rating.setKnowledgeableScore(11);

        Set<ConstraintViolation<StaffRating>> violations =
                validator.validate(rating);

        assertFalse(violations.isEmpty());
    }

    @Test
    void commentTooLongShouldBeRejected() {
        StaffRating rating = createValidRating();

        rating.setComment("A".repeat(501));

        Set<ConstraintViolation<StaffRating>> violations =
                validator.validate(rating);

        assertFalse(violations.isEmpty());
    }
}