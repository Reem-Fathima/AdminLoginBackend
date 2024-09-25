package com.ust.project.sustainability.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Represents the answers to a specific goal, including the responses.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GoalAnswers {
    private String questionId;
    private List<Response> response;
}
