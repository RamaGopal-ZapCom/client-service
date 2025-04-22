package com.zapcom.repository;

import com.zapcom.entity.ClientServiceRootSchema;
import com.zapcom.model.request.ClientServiceRootRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Rama Gopal
 * Project Name - client-service
 */
public interface ClientServiceRepository extends MongoRepository<ClientServiceRootSchema,String> {
}
