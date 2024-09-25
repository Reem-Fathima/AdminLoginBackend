package com.ust.project.sustainability.mapper;


import com.ust.project.sustainability.dto.SupplierGoalDTO;
;
import com.ust.project.sustainability.model.Question;
import com.ust.project.sustainability.model.SupplierGoals;
import org.springframework.stereotype.Component;

@Component
public class SupplierGoalsMapper {

    /**
     * Maps a SupplierGoals entity to a SupplierGoalDTO.
     *
     * @param supplierGoals The SupplierGoals entity to map.
     * @param question      The Question object to include in the DTO.
     * @param goalDescription The goal description after replacing placeholders.
     * @return A mapped SupplierGoalDTO object.
     */
    public SupplierGoalDTO supplierGoalDTOMapping(SupplierGoals supplierGoals, Question question, String goalDescription) {
        return SupplierGoalDTO.builder()
                .supplierName(supplierGoals.getSupplierName())
                .supplierId(supplierGoals.getSupplierId())
                .pillarName(supplierGoals.getPillarName())
                .questionId(question.getQuestionId())
                .goalDescription(goalDescription)
                .goalType(supplierGoals.getGoalType())
                .smartGoal(supplierGoals.isSmartGoal() ? "Yes" : "No")
                .goalCreationYear(supplierGoals.getGoalCreationYear())
                .updatedDt(supplierGoals.getUpdatedDt())
                .updatedUser(supplierGoals.getUpdatedUser())
                .build();
    }
}
