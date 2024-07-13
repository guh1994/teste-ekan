package com.example.ekan.api.controller;

import com.example.ekan.api.model.Beneficiary;
import com.example.ekan.api.model.Document;
import com.example.ekan.api.reponse.RestEntityResponse;
import com.example.ekan.api.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiary")
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService service;

    @GetMapping
    public ResponseEntity<RestEntityResponse<List<Beneficiary>>> getAllBeneficiary() {

        RestEntityResponse<List<Beneficiary>> response = service.getAllBeneficiary();

        if (!response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("{id}")
    public ResponseEntity<RestEntityResponse<Beneficiary>> getBeneficiaryById(@PathVariable Integer id) {

        RestEntityResponse<Beneficiary> response = service.getBeneficiaryById(id);

        if (!response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/document/{id}")
    public ResponseEntity<RestEntityResponse<List<Document>>> getDocumentsByBeneficiaryId(@PathVariable Integer id) {

        RestEntityResponse<List<Document>> response = service.getAllDocumentsByBeneficiaryId(id);

        if (!response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestEntityResponse<Beneficiary>> createBeneficiary(@RequestBody Beneficiary beneficiary) {

        RestEntityResponse<Beneficiary> response = service.createBeneficiary(beneficiary);

        if (!response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RestEntityResponse<Beneficiary>> updateBeneficiary(@RequestBody Beneficiary beneficiary) {

        RestEntityResponse<Beneficiary> response = service.updateBeneficiary(beneficiary);

        if (!response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RestEntityResponse<Beneficiary>> deleteBeneficiary(@PathVariable Integer id) {

        RestEntityResponse<Beneficiary> response = service.deleteBeneficiary(id);

        if (!response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
