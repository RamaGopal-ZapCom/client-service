package com.zapcom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Created by Rama Gopal Project Name - client-service */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LegalAndTaxCompliance {
  public String gstNumber;
  public String panCard;
  public String fssaiLicense;
  public String shopsAndEstablishmentLicense;
  public String tradeLicense;
}
