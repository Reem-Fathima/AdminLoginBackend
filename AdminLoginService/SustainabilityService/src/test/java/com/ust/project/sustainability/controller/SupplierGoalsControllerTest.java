package com.ust.project.sustainability.controller;

import com.ust.project.sustainability.dto.SupplierGoalDTO;
import com.ust.project.sustainability.exception.SupplierNotFoundException;
import com.ust.project.sustainability.model.SupplierGoals;
import com.ust.project.sustainability.service.SupplierGoalsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplierGoalsControllerTest {

    @Mock
    private SupplierGoalsService supplierGoalsService;

    @InjectMocks
    private SupplierGoalsController supplierGoalsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSupplierGoalsById_success() {
        // Arrange
        String supplierId = "1234";
        List<SupplierGoals> mockSupplierGoals = List.of(new SupplierGoals());
        when(supplierGoalsService.getSupplierById(supplierId)).thenReturn(mockSupplierGoals);

        // Act
        ResponseEntity<List<SupplierGoals>> response = supplierGoalsController.getSupplierGoalsById(supplierId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSupplierGoals, response.getBody());
        verify(supplierGoalsService, times(1)).getSupplierById(supplierId);
    }

    @Test
    void testGetSupplierGoalsById_supplierNotFound() {
        // Arrange
        String supplierId = "123456";
        when(supplierGoalsService.getSupplierById(supplierId)).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(SupplierNotFoundException.class, () -> {
            supplierGoalsController.getSupplierGoalsById(supplierId);
        });
        verify(supplierGoalsService, times(1)).getSupplierById(supplierId);
    }

    @Test
    void testGetMappedSupplierGoals_success() {
        // Arrange
        String supplierId = "1234";
        List<SupplierGoalDTO> mockMappedSupplierGoals = List.of(new SupplierGoalDTO());
        when(supplierGoalsService.getMappedSupplierGoals(supplierId)).thenReturn(mockMappedSupplierGoals);

        // Act
        ResponseEntity<List<SupplierGoalDTO>> response = supplierGoalsController.getMappedSupplierGoals(supplierId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockMappedSupplierGoals, response.getBody());
        verify(supplierGoalsService, times(1)).getMappedSupplierGoals(supplierId);
    }

    @Test
    void testGetMappedSupplierGoals_supplierNotFound() {
        // Arrange
        String supplierId = "123456";
        when(supplierGoalsService.getMappedSupplierGoals(supplierId)).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(SupplierNotFoundException.class, () -> {
            supplierGoalsController.getMappedSupplierGoals(supplierId);
        });
        verify(supplierGoalsService, times(1)).getMappedSupplierGoals(supplierId);
    }
}
