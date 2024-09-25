package com.ust.project.sustainability.service;

import com.ust.project.sustainability.dto.SupplierGoalDTO;
import com.ust.project.sustainability.exception.SupplierNotFoundException;
import com.ust.project.sustainability.model.*;
import com.ust.project.sustainability.repository.GoalConfigRepo;
import com.ust.project.sustainability.repository.SupplierGoalsRepo;
import com.ust.project.sustainability.serviceImpl.SupplierGoalsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class SupplierGoalsServiceImplTest {

    @Mock
    private SupplierGoalsRepo supplierGoalsRepo;

    @Mock
    private GoalConfigRepo goalConfigRepo;

    @InjectMocks
    private SupplierGoalsServiceImpl supplierGoalsService;

    private SupplierGoals mockSupplierGoals;
    private GoalConfig mockGoalConfig;

    @BeforeEach
    void setUp() {
        mockSupplierGoals = new SupplierGoals();
        mockSupplierGoals.setSupplierId("S001");
        mockSupplierGoals.setSupplierName("Test Supplier");
        mockSupplierGoals.setPillarName("Test Pillar");
        mockSupplierGoals.setGoalType("Test Type");
        mockSupplierGoals.setSmartGoal(true);
        mockSupplierGoals.setGoalCreationYear("2023");
        mockSupplierGoals.setUpdatedDt("2023-09-19");
        mockSupplierGoals.setUpdatedUser("Test User");
        GoalAnswers goalAnswers = new GoalAnswers();
        Response response = new Response();
        response.setFieldId("field1");
        response.setValue("value1");
        List<Response> responses = new ArrayList<>();
        responses.add(response);
        goalAnswers.setResponse((ArrayList<Response>) responses);
        List<GoalAnswers> goalAnswersList = new ArrayList<>();
        goalAnswersList.add(goalAnswers);
        mockSupplierGoals.setGoalAnswers((ArrayList<GoalAnswers>) goalAnswersList);
        mockGoalConfig = new GoalConfig();
        mockGoalConfig.setPillarName("Test Pillar");
        Question question = new Question();
        question.setQuestionId("Q001");
        question.setText("Test question with <field1>");
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        mockGoalConfig.setQuestions(questions);
    }

    @Test
    void getSupplierById_Success() {
        when(supplierGoalsRepo.findBySupplierId("S001")).thenReturn(Collections.singletonList(mockSupplierGoals));
        List<SupplierGoals> result = supplierGoalsService.getSupplierById("S001");
        assertFalse(result.isEmpty());
        assertEquals("S001", result.get(0).getSupplierId());
        verify(supplierGoalsRepo).findBySupplierId("S001");
    }

    @Test
    void getSupplierById_NotFound() {
        when(supplierGoalsRepo.findBySupplierId("S002")).thenReturn(Collections.emptyList());
        assertThrows(SupplierNotFoundException.class, () -> supplierGoalsService.getSupplierById("S002"));
        verify(supplierGoalsRepo).findBySupplierId("S002");
    }

    @Test
    void getSupplierGoalPlaceholders_Success() {
        when(supplierGoalsRepo.findBySupplierId("S001")).thenReturn(Collections.singletonList(mockSupplierGoals));
        Map<String, String> result = supplierGoalsService.getSupplierGoalPlaceholders("S001");
        assertFalse(result.isEmpty());
        assertEquals("value1", result.get("<field1>"));
        verify(supplierGoalsRepo).findBySupplierId("S001");
    }



    @Test
    void getMappedSupplierGoals_NoGoalConfig() {
        when(supplierGoalsRepo.findBySupplierId("S001")).thenReturn(Collections.singletonList(mockSupplierGoals));
        when(goalConfigRepo.findBySupplierId("S001")).thenReturn(Collections.emptyList());
        List<SupplierGoalDTO> result = supplierGoalsService.getMappedSupplierGoals("S001");
        assertTrue(result.isEmpty());
        verify(supplierGoalsRepo).findBySupplierId("S001");
        verify(goalConfigRepo).findBySupplierId("S001");
    }

    @Test
    void getMappedSupplierGoals_NoMatchingGoalConfig() {
        SupplierGoals differentPillarGoals = new SupplierGoals();
        differentPillarGoals.setPillarName("Different Pillar");
        when(supplierGoalsRepo.findBySupplierId("S001")).thenReturn(Collections.singletonList(differentPillarGoals));
        when(goalConfigRepo.findBySupplierId("S001")).thenReturn(Collections.singletonList(mockGoalConfig));
        List<SupplierGoalDTO> result = supplierGoalsService.getMappedSupplierGoals("S001");
        assertTrue(result.isEmpty());
        verify(supplierGoalsRepo).findBySupplierId("S001");
        verify(goalConfigRepo).findBySupplierId("S001");
    }

    @Test
    void replacePlaceholders_Success() {
        String questionText = "Test question with <field1>";
        String result = supplierGoalsService.replacePlaceholders(questionText, mockSupplierGoals);
        assertEquals("Test question with \"value1\"", result);
    }
}