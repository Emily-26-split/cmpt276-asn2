package com.example.asn2;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class StaffRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @NotNull(message = "Clarity score is required")
    @Min(value = 1, message = "Clarity must be at least 1")
    @Max(value = 10, message = "Clarity cannot exceed 10")
    private Integer clarity;

    @NotNull(message = "Niceness score is required")
    @Min(value = 1, message = "Niceness must be at least 1")
    @Max(value = 10, message = "Niceness cannot exceed 10")
    private Integer niceness;

    @NotNull(message = "Knowledge score is required")
    @Min(value = 1, message = "Knowledge score must be at least 1")
    @Max(value = 10, message = "Knowledge score cannot exceed 10")
    private Integer knowledgeableScore;

    @Size(max = 500, message = "Comment cannot exceed 500 characters")
    @Column(length = 500)
    private String comment;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public StaffRating() {
    }

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Double getOverallScore() {
        if (clarity == null || niceness == null || knowledgeableScore == null) {
            return null;
        }

        return (clarity + niceness + knowledgeableScore) / 3.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Integer getClarity() {
        return clarity;
    }

    public void setClarity(Integer clarity) {
        this.clarity = clarity;
    }

    public Integer getNiceness() {
        return niceness;
    }

    public void setNiceness(Integer niceness) {
        this.niceness = niceness;
    }

    public Integer getKnowledgeableScore() {
        return knowledgeableScore;
    }

    public void setKnowledgeableScore(Integer knowledgeableScore) {
        this.knowledgeableScore = knowledgeableScore;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}