package com.zapcom.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** Created by Rama Gopal Project Name - client-service */
@Configuration
public class ClientServiceConfiguration {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  private MongoDatabaseFactory mongoDatabaseFactory;

  @Autowired
  private MongoMappingContext mongoMappingContext;

  @Bean
  public MappingMongoConverter mappingMongoConverter() {

    DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory);
    MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
    converter.setTypeMapper(new DefaultMongoTypeMapper(null));
    return converter;

  }
}
