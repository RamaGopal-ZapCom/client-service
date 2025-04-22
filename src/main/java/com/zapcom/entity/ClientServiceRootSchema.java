package com.zapcom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** Created by Rama Gopal Project Name - client-service */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "client_service_schema")
public class ClientServiceRootSchema {
  @Id private String id;
  public ClientProfile clientProfile;
  public LegalAndTaxCompliance legalAndTaxCompliance;
  public BankingDetails bankingDetails;
  public AdminDetails adminDetails;
  public MetaData metaData;
  public Operations operations;
  public ApiConfiguration apiConfiguration;
  public Branding branding;
  public ChatbotConfiguration chatbotConfig;
  public Agreements agreements;
}
