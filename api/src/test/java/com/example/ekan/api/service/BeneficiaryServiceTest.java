package com.example.ekan.api.service;

import com.example.ekan.api.model.Beneficiary;
import com.example.ekan.api.model.Document;
import com.example.ekan.api.reponse.RestEntityResponse;
import com.example.ekan.api.repository.BeneficiaryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BeneficiaryServiceTest {

    public static final Integer ID = 1;
    public static final String NAME = "TESTE DA EKAN";
    public static final LocalDate BORN_DATE = LocalDate.of(1999, 01, 01);
    public static final String PHONE = "1122334455";
    public static final String DOCUMENT_TYPE = "Curriculo";
    public static final String DESCRIPTION = "Curriculo";

    @InjectMocks
    public BeneficiaryService subjet;

    @Mock
    public Beneficiary beneficiary;

    @Mock
    public Document document;

    @Mock
    public BeneficiaryRepository repository;

    @BeforeEach
    public void setup() {

        Mockito.when(beneficiary.getId()).thenReturn(ID);
        Mockito.when(beneficiary.getName()).thenReturn(NAME);
        Mockito.when(beneficiary.getPhone()).thenReturn(PHONE);
        Mockito.when(beneficiary.getBornDate()).thenReturn(BORN_DATE);
        Mockito.when(beneficiary.getDocument()).thenReturn(List.of(document));
        Mockito.when(document.getDocumentType()).thenReturn(DOCUMENT_TYPE);
        Mockito.when(document.getDescription()).thenReturn(DESCRIPTION);
        Mockito.when(repository.findAll()).thenReturn(List.of(beneficiary));
        Mockito.when(repository.findById(ID)).thenReturn(Optional.of(beneficiary));
        Mockito.when(repository.save(any())).thenReturn(beneficiary);
    }

    @Test
    public void shouldGetAllBeneficiary(){

        RestEntityResponse<List<Beneficiary>> response = subjet.getAllBeneficiary();

        Assertions.assertEquals(1,response.getEntity().size());
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(List.of(beneficiary) , response.getEntity());
    }

    @Test
    public void shouldReturnErrorMessageWhenGetAllBeneficiary(){
        Mockito.when(repository.findAll()).thenReturn(Collections.emptyList());

        RestEntityResponse<List<Beneficiary>> response = subjet.getAllBeneficiary();

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(List.of("Beneficiary Not Found"),response.getMessages());
    }

    @Test
    public void shouldGetBeneficiaryById(){

        RestEntityResponse<Beneficiary> response = subjet.getBeneficiaryById(beneficiary.getId());

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(beneficiary , response.getEntity());

    }

    @Test
    public void shouldReturnErrorMessageWhenGetBeneficiaryById(){
        Mockito.when(repository.findById(beneficiary.getId())).thenReturn(Optional.empty());

        RestEntityResponse<Beneficiary> response = subjet.getBeneficiaryById(beneficiary.getId());

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(List.of("Beneficiary not found"),response.getMessages());
    }

    @Test
    public void shouldGetDocumentsByBeneficiaryId(){

        RestEntityResponse<List<Document>> response = subjet.getAllDocumentsByBeneficiaryId(beneficiary.getId());

        Assertions.assertEquals(1,response.getEntity().size());
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(List.of(document) , response.getEntity());
    }

    @Test
    public void shouldReturnErrorMessageWhenGetDocumentsByBeneficiaryIdWhenFindBeneficiary(){
        Mockito.when(repository.findById(beneficiary.getId())).thenReturn(Optional.empty());

        RestEntityResponse<List<Document>> response = subjet.getAllDocumentsByBeneficiaryId(beneficiary.getId());

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(List.of("Beneficiary not found"),response.getMessages());
    }

    @Test
    public void shouldReturnErrorMessageWhenGetDocumentsByBeneficiaryId(){
        Mockito.when(beneficiary.getDocument()).thenReturn(Collections.emptyList());

        RestEntityResponse<List<Document>> response = subjet.getAllDocumentsByBeneficiaryId(beneficiary.getId());

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(List.of("No documents founded"),response.getMessages());
    }

    @Test
    public void shouldCreateBeneficiary(){

        Mockito.when(repository.findById(beneficiary.getId())).thenReturn(Optional.empty());

        RestEntityResponse<Beneficiary> response = subjet.createBeneficiary(beneficiary);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(beneficiary,response.getEntity());
    }

    @Test
    public void shoulReturnErrorNameIsNullWhenCreateBeneficiary(){
        Mockito.when(beneficiary.getName()).thenReturn("");

        RestEntityResponse<Beneficiary> response = subjet.createBeneficiary(beneficiary);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(List.of("Name is null"),response.getMessages());

    }

    @Test
    public void shouldReturnErrorMessagePhoneIsNullWhenCreateBeneficiary(){
        Mockito.when(beneficiary.getPhone()).thenReturn("");

        RestEntityResponse<Beneficiary> response = subjet.createBeneficiary(beneficiary);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(List.of("Phone is null"),response.getMessages());
    }

    @Test
    public void shouldReturnErrorMessageBornAgeIsNulLWhenCreateBeneficiary(){
        Mockito.when(beneficiary.getBornDate()).thenReturn(null);

        RestEntityResponse<Beneficiary> response = subjet.createBeneficiary(beneficiary);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(List.of("Born age is null"),response.getMessages());

    }

    @Test
    public void shoulReturnErrorMessageBeneficiaryAlreadyExistWhenCreateBeneficiary(){
        RestEntityResponse<Beneficiary> response = subjet.createBeneficiary(beneficiary);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(List.of("Beneficiary already exist"),response.getMessages());
    }

    @Test
    public void shouldUpdateBeneficiary(){

        RestEntityResponse<Beneficiary> response = subjet.updateBeneficiary(beneficiary);

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(beneficiary,response.getEntity());
    }

    @Test
    public void shoulReturnErrorMessageNameIsNullWhenUpdateBeneficiary(){
        Mockito.when(beneficiary.getName()).thenReturn("");

        RestEntityResponse<Beneficiary> response = subjet.updateBeneficiary(beneficiary);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(List.of("Name is null"),response.getMessages());
    }

    @Test
    public void shouldReturnErrorMessagePhoneIsNullWhenUpdateBeneficiary(){
        Mockito.when(beneficiary.getPhone()).thenReturn("");

        RestEntityResponse<Beneficiary> response = subjet.createBeneficiary(beneficiary);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(List.of("Phone is null"),response.getMessages());
    }
    @Test
    public void shouldReturnErrorMessageBornAgeIsNulLWhenUpdateBeneficiary(){
        Mockito.when(beneficiary.getBornDate()).thenReturn(null);

        RestEntityResponse<Beneficiary> response = subjet.updateBeneficiary(beneficiary);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(List.of("Born age is null"),response.getMessages());

    }

    @Test
    public void shoulReturnErrorMessageBeneficiaryNotFoundWhenUpdateBeneficiary(){
        Mockito.when(repository.findById(beneficiary.getId())).thenReturn(Optional.empty());

        RestEntityResponse<Beneficiary> response = subjet.updateBeneficiary(beneficiary);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(List.of("Beneficiary not found"),response.getMessages());
    }


    @Test
    public void shouldDeleteBeneficiaryById(){
        RestEntityResponse<Beneficiary> response = subjet.deleteBeneficiary(beneficiary.getId());

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(List.of("Beneficiary Deleted"),response.getMessages());

    }

    @Test
    public void shouldReturnBeneficiaryDoesnExistWhenTryDelete(){

        RestEntityResponse<Beneficiary> response = subjet.deleteBeneficiary(null);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertEquals(List.of("Beneficiary doesn't exists, can't delete"),response.getMessages());
    }
}
