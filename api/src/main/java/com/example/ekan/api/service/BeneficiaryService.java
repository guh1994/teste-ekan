package com.example.ekan.api.service;

import com.example.ekan.api.model.Beneficiary;
import com.example.ekan.api.model.Document;
import com.example.ekan.api.reponse.RestEntityResponse;
import com.example.ekan.api.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaryService {

    @Autowired
    private BeneficiaryRepository repository;

    public RestEntityResponse<List<Beneficiary>> getAllBeneficiary() {

        List<Beneficiary> beneficiarys = repository.findAll();

        if (beneficiarys.isEmpty()) {
            return RestEntityResponse
                    .<List<Beneficiary>>builder()
                    .success(false)
                    .messages(List.of("Beneficiary Not Found"))
                    .build();
        }

        return RestEntityResponse.<List<Beneficiary>>builder().success(true).entity(beneficiarys).build();
    }

    public RestEntityResponse<Beneficiary> getBeneficiaryById(Integer id) {

        Optional<Beneficiary> beneficiaryById = repository.findById(id);
        if (beneficiaryById.isEmpty()){
            return RestEntityResponse.<Beneficiary>builder()
                    .success(false)
                    .messages(List.of("Beneficiary not found"))
                    .build();
        }

        return RestEntityResponse.<Beneficiary>builder().entity(beneficiaryById.get()).success(true).build();
    }

    public RestEntityResponse<List<Document>> getAllDocumentsByBeneficiaryId(Integer id) {
        return RestEntityResponse.<List<Document>>builder().build();
    }

    public RestEntityResponse<Beneficiary> createBeneficiary(Beneficiary beneficiary) {

        List<String> validate = validateBeneficiary(beneficiary);

        if (!validate.isEmpty()){
           return RestEntityResponse.<Beneficiary>builder()
                   .success(false)
                   .messages(validate)
                   .build();
        }
        Optional<Beneficiary> beneficiaryById = repository.findById(beneficiary.getId());

        if (beneficiaryById.isPresent()){
            return RestEntityResponse.<Beneficiary>builder()
                    .success(false)
                    .messages(List.of("User already exist"))
                    .build();
        }
        Beneficiary beneficiaryCreated = repository.save(beneficiary);

        return RestEntityResponse.<Beneficiary>builder().success(true).entity(beneficiaryCreated).build();
    }

    public RestEntityResponse<Beneficiary> updateBeneficiary(Beneficiary beneficiary) {
        return RestEntityResponse.<Beneficiary>builder().build();
    }

    public void deleteBeneficiary(Integer id) {

    }

    private List<String> validateBeneficiary(Beneficiary beneficiary){
        List<String> messages = new ArrayList<>();
        if (beneficiary.getName().isEmpty()){
            messages.add("Name is null");
            return messages;
        }
        if (beneficiary.getPhone().isEmpty()){
            messages.add("Phone is null");
        }
        if (beneficiary.getBornDate() == null){
            messages.add("Born age is null");
        }
        return messages;

    }
}
