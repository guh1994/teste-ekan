package com.example.ekan.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String documentType;

    private String description;

    private LocalDate inclusionDate;

    private LocalDate updateDate;

    @PrePersist
    protected void onCreate() {
        inclusionDate = LocalDate.now();
        updateDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = LocalDate.now();
    }

}
