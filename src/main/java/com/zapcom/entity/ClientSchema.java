package com.zapcom.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Document(collection = "customer_tab")
@Schema(description = "Schema representing a client registering for the service")
public class ClientSchema {

  @Id
  @Schema(description = "Business email (acts as unique identifier)", example = "demo@gmail.com")
  private String businessEmail;

  @Schema(description = "Business name of the client", example = "Zapcom Pvt Ltd")
  private String businessName;

  @Schema(description = "Owner's full name", example = "Rama Gopal")
  private String ownerName;

  @Schema(description = "Contact mobile number", example = "+91-9876543210")
  private String contactNumber;

  @Schema(description = "Password for authentication", example = "Test@123")
  private String password;

  @Schema(description = "GST Number of the client", example = "29ABCDE1234F2Z5")
  private String gstNumber;

  @Schema(description = "PAN card number", example = "ABCDE1234F")
  private String panCard;

  @Schema(description = "Bank account number", example = "12345678901234")
  private String bankAccountNumber;

  @Schema(description = "Name of the client's bank", example = "HDFC Bank")
  private String bankName;

  @Schema(description = "IFSC Code of the bank branch", example = "HDFC0001234")
  private String ifscCode;

  @Schema(description = "Client API token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
  private String token;

  @Schema(description = "Business address details")
  private Address businessAddress;
}
