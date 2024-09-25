package com.ust.project.sustainability.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a response to a specific question in the goal answers.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {
    private String fieldId;
    private String value;
}
