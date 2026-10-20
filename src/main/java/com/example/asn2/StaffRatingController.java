package com.example.asn2;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class StaffRatingController {

    private final StaffRatingRepository repository;

    public StaffRatingController(StaffRatingRepository repository) {
        this.repository = repository;
    }

    // READ: Display all staff ratings
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("ratings", repository.findAll());
        return "index";
    }

    // READ: Display a single rating
    @GetMapping("/ratings/{id}")
    public String detail(@PathVariable Long id, Model model) {
        StaffRating rating = repository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND,
                        "Rating not found"));

        // Use polymorphism to display the staff member's title
        StaffMemberProfile profile;

        if (rating.getRoleType() == RoleType.TA) {
            profile = new TAProfile();
        } else if (rating.getRoleType() == RoleType.PROF) {
            profile = new ProfessorProfile();
        } else {
            profile = null;
        }

        model.addAttribute("rating", rating);

        if (profile != null) {
            model.addAttribute("displayTitle", profile.displayTitle());
        } else {
            model.addAttribute("displayTitle", rating.getRoleType().toString());
        }

        return "detail";
    }

    // CREATE: Show form
    @GetMapping("/ratings/new")
    public String createForm(Model model) {
        model.addAttribute("rating", new StaffRating());
        model.addAttribute("roles", RoleType.values());
        return "form";
    }

    // CREATE: Save new rating
    @PostMapping("/ratings")
    public String create(
            @Valid @ModelAttribute("rating") StaffRating rating,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("roles", RoleType.values());
            return "form";
        }

        repository.save(rating);
        return "redirect:/";
    }

    // UPDATE: Show edit form
    @GetMapping("/ratings/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        StaffRating rating = repository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND,
                        "Rating not found"));

        model.addAttribute("rating", rating);
        model.addAttribute("roles", RoleType.values());
        return "form";
    }

    // UPDATE: Save changes
    @PostMapping("/ratings/{id}")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("rating") StaffRating formRating,
            BindingResult result,
            Model model) {

        StaffRating existing = repository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND,
                        "Rating not found"));

        if (result.hasErrors()) {
            model.addAttribute("roles", RoleType.values());
            return "form";
        }

        existing.setName(formRating.getName());
        existing.setEmail(formRating.getEmail());
        existing.setRoleType(formRating.getRoleType());
        existing.setClarity(formRating.getClarity());
        existing.setNiceness(formRating.getNiceness());
        existing.setKnowledgeableScore(formRating.getKnowledgeableScore());
        existing.setComment(formRating.getComment());

        repository.save(existing);
        return "redirect:/ratings/" + id;
    }

    // DELETE: Remove rating
    @PostMapping("/ratings/{id}/delete")
    public String delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    "Rating not found");
        }

        repository.deleteById(id);
        return "redirect:/";
    }
}