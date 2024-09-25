package com.ust.project.sustainability.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an input field used in a question.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputField {
    private String inputId;
    private String label;
}
