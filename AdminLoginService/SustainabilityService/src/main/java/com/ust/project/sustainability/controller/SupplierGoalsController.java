package com.ust.project.sustainability.controller;

import com.ust.project.sustainability.dto.SupplierGoalDTO;
import com.ust.project.sustainability.exception.SupplierNotFoundException;
import com.ust.project.sustainability.model.SupplierGoals;
import com.ust.project.sustainability.service.SupplierGoalsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling supplier goals related API requests.
 */
@Slf4j
@RestController
@RequestMapping("/sustainability")
@Api(value = "Supplier Goals", tags = {"Supplier Goals Controller"})
public class SupplierGoalsController {

    private final SupplierGoalsService supplierGoalsService;

    /**
     * Constructor for SupplierGoalsController.
     *
     * @param supplierGoalsService the service layer to handle business logic for supplier goals
     */
    public SupplierGoalsController(SupplierGoalsService supplierGoalsService) {
        this.supplierGoalsService = supplierGoalsService;
    }

    /**
     * Retrieves supplier goals by supplier ID.
     *
     * @param supplierId the ID of the supplier
     * @return ResponseEntity containing the list of SupplierGoals
     */
    @ApiOperation(value = "Get Supplier Goals by ID", notes = "Fetches all the goals for the supplier using their supplier ID.")
    @GetMapping("/{supplierId}")
    public ResponseEntity<List<SupplierGoals>> getSupplierGoalsById(@PathVariable String supplierId) {
        log.info("Request received to get SupplierGoals with ID: {}", supplierId);
        List<SupplierGoals> supplierGoals = supplierGoalsService.getSupplierById(supplierId);
        if (supplierGoals.isEmpty()) {
            throw new SupplierNotFoundException(String.format("No SupplierGoals found for Supplier ID: %s", supplierId));
        }
        return new ResponseEntity<>(supplierGoals, HttpStatus.OK);
    }

    /**
     * Retrieves mapped supplier goals by supplier ID.
     *
     * @param supplierId the ID of the supplier
     * @return ResponseEntity containing the list of mapped SupplierGoalDTOs
     */
    @ApiOperation(value = "Get Mapped Supplier Goals by ID", notes = "Fetches the mapped supplier goals for the supplier using their supplier ID.")
    @GetMapping("/mapped-supplier-goals/{supplierId}")
    public ResponseEntity<List<SupplierGoalDTO>> getMappedSupplierGoals(@PathVariable String supplierId) {
        log.info("getMappedSupplierGoals controller started with supplierId: {}", supplierId);
        List<SupplierGoalDTO> mappedSupplierGoals = supplierGoalsService.getMappedSupplierGoals(supplierId);
        if (mappedSupplierGoals.isEmpty()) {
            throw new SupplierNotFoundException(String.format("No mapped SupplierGoals found for Supplier ID: %s", supplierId));
        }
        return new ResponseEntity<>(mappedSupplierGoals, HttpStatus.OK);
    }
}
