package com.zapcom.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Rama Gopal
 * Project Name - client-service
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientServiceRootRequest {
    public ClientProfileRequest clientProfileRequest;
    public LegalAndTaxComplianceRequest legalAndTaxCompliance;
    public BankingDetailsRequest bankingDetailsRequest;
    public AdminDetailsRequest adminDetailsRequest;
}
