package com.ust.project.sustainability.repository;

import com.ust.project.sustainability.model.GoalConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on GoalConfig documents in MongoDB.
 */
public interface GoalConfigRepo extends MongoRepository<GoalConfig, String> {
    /**
     * Finds all GoalConfig documents associated with the specified supplier ID.
     *
     * @param supplierId The ID of the supplier whose GoalConfig documents are to be retrieved.
     * @return A list of GoalConfig documents associated with the specified supplier ID.
     */
    List<GoalConfig> findBySupplierId(String supplierId);
}
