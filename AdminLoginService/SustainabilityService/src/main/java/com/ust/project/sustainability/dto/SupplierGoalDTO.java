package com.ust.project.sustainability.dto;
import lombok.Builder;
import lombok.*;

/**
 * Data Transfer Object (DTO) for representing supplier goal details.
 * Used to transfer goal-related data between layers.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupplierGoalDTO {
    private String supplierName;
    private String supplierId;
    private String pillarName;
    private String questionId;
    private String goalDescription;
    private String goalType;
    private String smartGoal;
    private String goalCreationYear;
    private String updatedDt;
    private String updatedUser;
}
