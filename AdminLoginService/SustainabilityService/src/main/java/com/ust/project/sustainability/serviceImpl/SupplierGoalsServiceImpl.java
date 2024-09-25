package com.ust.project.sustainability.serviceImpl;

import com.ust.project.sustainability.dto.SupplierGoalDTO;
import com.ust.project.sustainability.exception.SupplierNotFoundException;
import com.ust.project.sustainability.mapper.SupplierGoalsMapper;
import com.ust.project.sustainability.model.*;
import com.ust.project.sustainability.repository.GoalConfigRepo;
import com.ust.project.sustainability.repository.SupplierGoalsRepo;
import com.ust.project.sustainability.service.SupplierGoalsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SupplierGoalsServiceImpl implements SupplierGoalsService {

    private final SupplierGoalsRepo supplierGoalsRepo;
    private final GoalConfigRepo goalConfigRepo;
    private final SupplierGoalsMapper supplierGoalsMapper;

    /**
     * Constructor for SupplierGoalsServiceImpl.
     *
     * @param supplierGoalsRepo repository for accessing supplier goals data
     * @param goalConfigRepo repository for accessing goal configurations
     * @param supplierGoalsMapper mapper for converting models to DTOs
     */
    @Autowired
    public SupplierGoalsServiceImpl(SupplierGoalsRepo supplierGoalsRepo, GoalConfigRepo goalConfigRepo, SupplierGoalsMapper supplierGoalsMapper) {
        this.supplierGoalsRepo = supplierGoalsRepo;
        this.goalConfigRepo = goalConfigRepo;
        this.supplierGoalsMapper = supplierGoalsMapper;
    }
    /**
     * Retrieves supplier goals by supplier ID.
     *
     * @param supplierId the ID of the supplier
     * @return List of SupplierGoals
     */
    @Override
    public List<SupplierGoals> getSupplierById(String supplierId) {
        List<SupplierGoals> supplierGoals = supplierGoalsRepo.findBySupplierId(supplierId);
        if (supplierGoals == null || supplierGoals.isEmpty()) {
            log.error("SupplierGoals with ID: {} not found", supplierId);
            throw new SupplierNotFoundException(supplierId);
        }
        log.info("SupplierGoals with ID: {} found", supplierId);
        return supplierGoals;
    }

    /**
     * Retrieves placeholders for a supplier's goals based on the goal answers.
     *
     * @param supplierId the ID of the supplier
     * @return Map of placeholders and their respective values
     */
    public Map<String, String> getSupplierGoalPlaceholders(String supplierId) {
        List<SupplierGoals> supplierGoals = getSupplierById(supplierId);
        Map<String, String> placeholders = supplierGoals.stream()
                .flatMap(goal -> goal.getGoalAnswers().stream())
                .flatMap(answer -> answer.getResponse().stream())
                .collect(Collectors.toMap(
                        response -> "<" + response.getFieldId() + ">",
                        Response::getValue
                ));
        log.info("Retrieved placeholders for Supplier ID: {}", supplierId);
        return placeholders;
    }

    /**
     * Retrieves mapped supplier goals and converts them to DTOs.
     *
     * @param supplierId the ID of the supplier
     * @return List of SupplierGoalDTOs
     */
    @Override
    public List<SupplierGoalDTO> getMappedSupplierGoals(String supplierId) {
        List<SupplierGoals> supplierGoalsList = supplierGoalsRepo.findBySupplierId(supplierId);
        List<GoalConfig> goalConfigs = goalConfigRepo.findBySupplierId(supplierId);
        List<SupplierGoalDTO> mappedSupplierGoals = new ArrayList<>();
        if (supplierGoalsList.isEmpty() || goalConfigs.isEmpty()) {
            log.warn("No SupplierGoals or GoalConfig found for Supplier ID: {}", supplierId);
            return mappedSupplierGoals;
        }
        for (SupplierGoals supplierGoals : supplierGoalsList) {
            log.info("Processing SupplierGoals for Pillar: {}", supplierGoals.getPillarName());
            GoalConfig matchingGoalConfig = goalConfigs.stream()
                    .filter(config -> config.getPillarName().equals(supplierGoals.getPillarName()))
                    .findFirst()
                    .orElse(null);
            if (matchingGoalConfig != null) {
                for (Question question : matchingGoalConfig.getQuestions()) {
                    String goalDescription = replacePlaceholders(question.getText(), supplierGoals);
                    SupplierGoalDTO supplierGoalDTO = supplierGoalsMapper.supplierGoalDTOMapping(supplierGoals, question, goalDescription);
                    mappedSupplierGoals.add(supplierGoalDTO);
                }
            } else {
                log.warn("No matching GoalConfig found for Pillar: {}", supplierGoals.getPillarName());
            }
        }
        log.info("Mapped {} SupplierGoalDTOs for Supplier ID: {}", mappedSupplierGoals.size(), supplierId);
        return mappedSupplierGoals;
    }

    public String replacePlaceholders(String questionText, SupplierGoals supplierGoals) {
        for (GoalAnswers goalAnswer : supplierGoals.getGoalAnswers()) {
            for (Response response : goalAnswer.getResponse()) {
                questionText = questionText.replace("<" + response.getFieldId() + ">", "\"" + response.getValue() + "\"");
            }
        }
        log.info("Replaced placeholders in the question text for SupplierGoals with ID: {}", supplierGoals.getSupplierId());
        return questionText;
    }
}
