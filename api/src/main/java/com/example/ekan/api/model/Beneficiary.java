package com.example.ekan.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "beneficiary")
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String phone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date bornDate;

    private LocalDate inclusionDate;

    private LocalDate updateDate;

    @OneToMany(targetEntity = Document.class, cascade = CascadeType.ALL)
    private List<Document> document;

    @PrePersist
    protected void onCreate() {
        inclusionDate = LocalDate.now();
        updateDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = LocalDate.now();
    }

    public void update(Beneficiary beneficiary){
        this.name = beneficiary.getName();
        this.phone = beneficiary.getPhone();
        this.bornDate = beneficiary.getBornDate();
        this.updateDate = beneficiary.getUpdateDate();
        this.document = beneficiary.getDocument();
    }

}
