package com.zapcom.service;

import com.zapcom.entity.ClientSchema;
import com.zapcom.model.request.ClientServiceRootRequest;
import com.zapcom.model.response.ClientResponse;
import com.zapcom.model.response.ClientServiceRootResponse;

/** Created by Rama Gopal Project Name - client-service */
public interface ClientService {
  String registerCustomer(ClientSchema customer);

  String resetToken(String email);

  ClientResponse getCustomerByEmail(String email);

    ClientServiceRootResponse clientRegister(ClientServiceRootRequest clientRootRequest);
}
