package com.zapcom.model.response;

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
public class ClientServiceRootResponse {
    public String customerName;
    public String customerEmail;
    public String jwtToken;
    public String message;
}
