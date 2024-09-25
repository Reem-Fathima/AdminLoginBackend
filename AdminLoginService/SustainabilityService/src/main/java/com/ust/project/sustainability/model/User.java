package com.ust.project.sustainability.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "user")
public class User {
//    @Id
//    private  String _id;
//    private String emailId;
//    private String adminDetailsId;
//    private String firstName;
//    private String lastName;
//    private String phoneNumber;
//    private boolean isActive;
@Id
private String id; // corresponds to "user_id_1" in your JSON
    private String emailId;
    private String role;
    private String adminDetailsId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String designation;
    private String refId; // corresponds to "sso_id"
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;
    private boolean isActive;

}
