package com.ust.project.sustainability.repository;


import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<com.ust.project.sustainability.model.User, String> {
    Optional<User> findByEmailId(String emailId);
}
