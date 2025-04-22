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
public class TechnicalRequest {
  public String technicalSupportName;
  public String technicalSupportEmail;
  public String technicalSupportPhone;
}
