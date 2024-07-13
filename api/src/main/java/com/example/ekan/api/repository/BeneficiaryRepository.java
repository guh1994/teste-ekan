package com.example.ekan.api.repository;

import com.example.ekan.api.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {
}
