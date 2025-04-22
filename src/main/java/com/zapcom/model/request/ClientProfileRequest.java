package com.zapcom.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Created by Rama Gopal Project Name - client-service */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientProfileRequest {
  public String customerName;
  public String industryType;
  public String businessCategory;
  public RegisteredAddressRequest registeredAddress;
  public String country;
  public String gstNumber;
  public String customerWebsite;
  public String customerRegistrationNumber;
  public String ownerName;
  public String contactNumber;
  public String customerEmail;
}
