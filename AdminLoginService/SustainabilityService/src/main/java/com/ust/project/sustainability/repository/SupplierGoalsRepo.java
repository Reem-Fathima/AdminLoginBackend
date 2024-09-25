package com.ust.project.sustainability.repository;

import com.ust.project.sustainability.model.SupplierGoals;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on SupplierGoals documents in MongoDB.
 */
@Repository
public interface SupplierGoalsRepo extends MongoRepository<SupplierGoals, String> {
    /**
     * Finds all SupplierGoals documents associated with the specified supplier ID.
     *
     * @param supplierId The ID of the supplier whose SupplierGoals documents are to be retrieved.
     * @return A list of SupplierGoals documents associated with the specified supplier ID.
     */
    List<SupplierGoals> findBySupplierId(String supplierId);
}
