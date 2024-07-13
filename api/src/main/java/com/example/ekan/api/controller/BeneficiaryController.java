package com.example.ekan.api.controller;

import com.example.ekan.api.model.Beneficiary;
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

    @PostMapping
    public ResponseEntity<RestEntityResponse<Beneficiary>> createBeneficiary(@RequestBody Beneficiary beneficiary) {

        RestEntityResponse<Beneficiary> response = service.createBeneficiary(beneficiary);

        if (!response.isSuccess()){
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}
