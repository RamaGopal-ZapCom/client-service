package com.zapcom.repository;

import com.zapcom.entity.ClientSchema;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/** Created by Rama Gopal Project Name - client-service */
@Repository
public interface ClientRepository extends MongoRepository<ClientSchema, Long> {
  Optional<ClientSchema> findByBusinessEmail(String email);
}
