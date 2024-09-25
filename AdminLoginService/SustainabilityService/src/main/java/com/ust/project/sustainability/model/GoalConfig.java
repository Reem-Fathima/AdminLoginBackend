package com.ust.project.sustainability.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Represents the configuration of goals, including the supplier ID, pillar name,
 * and a list of questions associated with the goals.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "goal_config")
public class GoalConfig {
    @Id
    private String _id;
    private String supplierId;
    private String pillarName;
    private List<Question> questions;
}
