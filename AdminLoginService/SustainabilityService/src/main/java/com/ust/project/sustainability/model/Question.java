package com.ust.project.sustainability.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a question in the goal configuration, including input fields.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question {
    private String questionId;
    private String text;
    private List<InputField> inputFields;
}
