package com.ust.project.sustainability.service;

import com.ust.project.sustainability.dto.SupplierGoalDTO;
import com.ust.project.sustainability.model.SupplierGoals;

import java.util.List;
import java.util.Map;

/**
 * Service interface for handling operations related to SupplierGoals entities.
 */
public interface SupplierGoalsService {
    /**
     * Retrieves all SupplierGoals entities associated with the specified supplier ID.
     *
     * @param supplierId The ID of the supplier whose SupplierGoals entities are to be retrieved.
     * @return A list of SupplierGoals entities associated with the specified supplier ID.
     */
    List<SupplierGoals> getSupplierById(String supplierId);

    /**
     * Retrieves a map of placeholders and their corresponding values for the specified supplier ID.
     *
     * @param supplierId The ID of the supplier whose goal placeholders are to be retrieved.
     * @return A map where the key is the placeholder and the value is the corresponding value.
     */
    Map<String, String> getSupplierGoalPlaceholders(String supplierId);

    /**
     * Retrieves a list of SupplierGoalDTO objects mapped from SupplierGoals and GoalConfig for the specified supplier ID.
     *
     * @param supplierId The ID of the supplier whose mapped supplier goals are to be retrieved.
     * @return A list of SupplierGoalDTO objects mapped from SupplierGoals and GoalConfig.
     */
    List<SupplierGoalDTO> getMappedSupplierGoals(String supplierId);
}
