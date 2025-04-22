package com.zapcom.service.impl;

import com.zapcom.entity.AdminDetails;
import com.zapcom.entity.BankingDetails;
import com.zapcom.entity.ClientProfile;
import com.zapcom.entity.ClientSchema;
import com.zapcom.entity.ClientServiceRootSchema;
import com.zapcom.entity.LegalAndTaxCompliance;
import com.zapcom.entity.Primary;
import com.zapcom.entity.RegisteredAddress;
import com.zapcom.entity.Technical;
import com.zapcom.model.AuthRequest;
import com.zapcom.model.request.AdminDetailsRequest;
import com.zapcom.model.request.BankingDetailsRequest;
import com.zapcom.model.request.ClientProfileRequest;
import com.zapcom.model.request.ClientServiceRootRequest;
import com.zapcom.model.request.LegalAndTaxComplianceRequest;
import com.zapcom.model.request.PrimaryRequest;
import com.zapcom.model.request.RegisteredAddressRequest;
import com.zapcom.model.request.TechnicalRequest;
import com.zapcom.model.response.ClientResponse;
import com.zapcom.model.response.ClientServiceRootResponse;
import com.zapcom.openfeign.AuthServiceClient;
import com.zapcom.repository.ClientRepository;
import com.zapcom.repository.ClientServiceRepository;
import com.zapcom.service.ClientService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/** Created by Rama Gopal Project Name - client-service */
@Service
public class ClientServiceImpl implements ClientService {

  private final ClientRepository customerRepository;

  private final AuthServiceClient authServiceClient;
  private final PasswordEncoder passwordEncoder;
  // used for actual-service
  private final ClientServiceRepository clientServiceRepository;

  @Value("${auth-service.base-url}")
  private String authServiceBaseUrl;

  public ClientServiceImpl(
      ClientRepository customerRepository,
      AuthServiceClient authServiceClient,
      PasswordEncoder passwordEncoder,
      ClientServiceRepository clientServiceRepository) {
    this.customerRepository = customerRepository;
    this.authServiceClient = authServiceClient;
    this.passwordEncoder = passwordEncoder;
    this.clientServiceRepository = clientServiceRepository;
  }

  // used webclient

  /*  @Override
  public String registerCustomer(Customer customer) {
      customerRepository.save(customer);
      String email = customer.getBusinessEmail();

      if (email == null || email.isEmpty()) {
          throw new IllegalArgumentException("Email cannot be null or empty");
      }
     // String authServiceUrl = "http://localhost:9991/auth/generateToken/" + email; // Replace with your actual auth-service URL
      String authServiceUrl = authServiceBaseUrl + "/generateToken/" + email;

      // Communicate with the auth-service to get the JWT token
      return webClientBuilder.baseUrl(authServiceUrl)
              .build()
              .post()
              .bodyValue(new AuthRequest(customer.getBusinessEmail())) // Assuming AuthRequest is a DTO for the request body
              .retrieve()
              .bodyToMono(String.class) // Assuming the token is returned as a string
              .block(); // Use `block()` to wait for the response (or you can use a reactive approach)
  }*/

  // using open-feign client without password encoded
  /*    @Override
  public String registerCustomer(Customer customer) {
      customerRepository.save(customer);
      String email = customer.getBusinessEmail();
      if (email == null || email.isEmpty()) {
          throw new IllegalArgumentException("Email cannot be null or empty");
      }
      AuthRequest authRequest = new AuthRequest(email);
      return authServiceClient.registerCustomer(email, authRequest);
  }*/

  // using open-feign client with password encoded
  @Override
  public String registerCustomer(ClientSchema customer) {
    // Encode password before saving
    String rawPassword = customer.getPassword();
    if (rawPassword == null || rawPassword.isEmpty()) {
      throw new IllegalArgumentException("Password cannot be null or empty");
    }

    String encodedPassword = passwordEncoder.encode(rawPassword);
    customer.setPassword(encodedPassword);

    customerRepository.save(customer);

    String email = customer.getBusinessEmail();
    if (email == null || email.isEmpty()) {
      throw new IllegalArgumentException("Email cannot be null or empty");
    }

    AuthRequest authRequest = new AuthRequest(email);
    return authServiceClient.registerCustomer(email, authRequest);
  }

  @Override
  public String resetToken(String email) {
    String authServiceUrl =
        "http://auth-service-url/auth/reset-token"; // Replace with your actual auth-service URL

    // Communicate with the auth-service to reset the token
    /*webClientBuilder.baseUrl(authServiceUrl)
    .build()
    .post()
    .bodyValue(new AuthRequest(email)) // Assuming AuthRequest is a DTO for the request body
    .retrieve()
    .bodyToMono(String.class) // Assuming the token is returned as a string
    .block(); // Wait for the response*/
    return "";
  }

  /*  public String registerCustomer(Customer customer) {
      customer.setToken(jwtUtil.generateToken(customer.getBusinessEmail()));
      repository.save(customer);
      return customer.getToken();
  }

  public String resetToken(String email) {
      Customer customer = repository.findByBusinessEmail(email)
              .orElseThrow(() -> new RuntimeException("Customer not found"));
      String newToken = jwtUtil.generateToken(email);
      customer.setToken(newToken);
      repository.save(customer);
      return newToken;
  }*/

  @Override
  public ClientResponse getCustomerByEmail(String email) {
    Optional<ClientSchema> optionalCustomer = customerRepository.findByBusinessEmail(email);
    ClientResponse response = null;
    if (optionalCustomer.isPresent()) {
      ClientSchema customer = optionalCustomer.get();
      response = new ClientResponse(customer.getBusinessEmail(), customer.getPassword());
    }
    return response;
  }

  @Override
  public ClientServiceRootResponse clientRegister(ClientServiceRootRequest clientRootRequest) {
    ClientProfileRequest clientProfileRequest = clientRootRequest.getClientProfileRequest();
    LegalAndTaxComplianceRequest legalAndTaxComplianceFromRoot =
        clientRootRequest.getLegalAndTaxCompliance();
    BankingDetailsRequest bankingDetailsRequestFromRoot =
        clientRootRequest.getBankingDetailsRequest();
    AdminDetailsRequest adminDetailsRequestFromRoot = clientRootRequest.getAdminDetailsRequest();
    RegisteredAddressRequest registeredAddressRequest = clientProfileRequest.getRegisteredAddress();
    PrimaryRequest primaryRequestFromAdmin = adminDetailsRequestFromRoot.getPrimaryRequest();
    TechnicalRequest technicalRequestFromAdmin = adminDetailsRequestFromRoot.getTechnicalRequest();

    LegalAndTaxComplianceRequest legalAndTaxComplianceRequest =
        LegalAndTaxComplianceRequest.builder()
            .gstNumber(legalAndTaxComplianceFromRoot.getGstNumber())
            .panCard(legalAndTaxComplianceFromRoot.getPanCard())
            .fssaiLicense(legalAndTaxComplianceFromRoot.getFssaiLicense())
            .shopsAndEstablishmentLicense(
                legalAndTaxComplianceFromRoot.getShopsAndEstablishmentLicense())
            .tradeLicense(legalAndTaxComplianceFromRoot.getTradeLicense())
            .build();
    BankingDetailsRequest bankingDetailsRequest =
        BankingDetailsRequest.builder()
            .bankAccountNumber(bankingDetailsRequestFromRoot.getBankAccountNumber())
            .bankName(bankingDetailsRequestFromRoot.getBankName())
            .ifscCode(bankingDetailsRequestFromRoot.getIfscCode())
            .cancelledChequeOrBankStatement(
                bankingDetailsRequestFromRoot.getCancelledChequeOrBankStatement())
            .build();

    PrimaryRequest primaryRequest =
        PrimaryRequest.builder()
            .name(primaryRequestFromAdmin.getName())
            .password(primaryRequestFromAdmin.getPassword())
            .phone(primaryRequestFromAdmin.getPhone())
            .address(primaryRequestFromAdmin.getAddress())
            .timeZone(primaryRequestFromAdmin.getTimeZone())
            .build();
    TechnicalRequest technicalRequest =
        TechnicalRequest.builder()
            .technicalSupportName(technicalRequestFromAdmin.getTechnicalSupportName())
            .technicalSupportEmail(technicalRequestFromAdmin.getTechnicalSupportEmail())
            .technicalSupportPhone(technicalRequestFromAdmin.getTechnicalSupportPhone())
            .build();

    AdminDetailsRequest adminDetailsRequest =
        AdminDetailsRequest.builder()
            .primaryRequest(primaryRequest)
            .technicalRequest(technicalRequest)
            .build();

    RegisteredAddress registeredAddress =
        RegisteredAddress.builder()
            .street(registeredAddressRequest.getStreet())
            .city(registeredAddressRequest.getCity())
            .state(registeredAddressRequest.getState())
            .pinCode(registeredAddressRequest.getPinCode())
            .build();

    ClientProfile customerProfile =
        ClientProfile.builder()
            .customerName(clientProfileRequest.getCustomerName())
            .industryType(clientProfileRequest.getIndustryType())
            .businessCategory(clientProfileRequest.getBusinessCategory())
            .registeredAddress(registeredAddress)
            .country(clientProfileRequest.getCountry())
            .gstNumber(clientProfileRequest.getGstNumber())
            .customerWebsite(clientProfileRequest.getCustomerWebsite())
            .customerRegistrationNumber(clientProfileRequest.getCustomerRegistrationNumber())
            .ownerName(clientProfileRequest.getOwnerName())
            .contactNumber(clientProfileRequest.getContactNumber())
            .customerEmail(clientProfileRequest.getCustomerEmail())
            .build();

    LegalAndTaxCompliance legalAndTaxCompliance =
        LegalAndTaxCompliance.builder()
            .gstNumber(legalAndTaxComplianceFromRoot.getGstNumber())
            .panCard(legalAndTaxComplianceFromRoot.getPanCard())
            .fssaiLicense(legalAndTaxComplianceFromRoot.getFssaiLicense())
            .shopsAndEstablishmentLicense(
                legalAndTaxComplianceFromRoot.getShopsAndEstablishmentLicense())
            .tradeLicense(legalAndTaxComplianceFromRoot.getTradeLicense())
            .build();

    Primary primary =
        Primary.builder()
            .name(primaryRequestFromAdmin.getName())
            .password(primaryRequestFromAdmin.getPassword())
            .phone(primaryRequestFromAdmin.getPhone())
            .address(primaryRequestFromAdmin.getAddress())
            .timeZone(primaryRequestFromAdmin.getTimeZone())
            .build();
    Technical technical =
        Technical.builder()
            .name(technicalRequestFromAdmin.getTechnicalSupportName())
            .email(technicalRequestFromAdmin.getTechnicalSupportEmail())
            .phone(technicalRequestFromAdmin.getTechnicalSupportPhone())
            .build();
    AdminDetails adminDetails =
        AdminDetails.builder().primary(primary).technical(technical).build();

    BankingDetails bankingDetails =
        BankingDetails.builder()
            .bankAccountNumber(bankingDetailsRequestFromRoot.getBankAccountNumber())
            .bankName(bankingDetailsRequestFromRoot.getBankName())
            .ifscCode(bankingDetailsRequestFromRoot.getIfscCode())
            .cancelledChequeOrBankStatement(
                bankingDetailsRequestFromRoot.getCancelledChequeOrBankStatement())
            .build();
    ClientServiceRootSchema clientServiceRootSchema =
        ClientServiceRootSchema.builder()
            .clientProfile(customerProfile)
            .legalAndTaxCompliance(legalAndTaxCompliance)
            .adminDetails(adminDetails)
            .bankingDetails(bankingDetails)
            .build();

    // Encode password before saving
    String rawPassword = primaryRequest.getPassword();
    if (rawPassword == null || rawPassword.isEmpty()) {
      throw new IllegalArgumentException("Password cannot be null or empty");
    }

    String encodedPassword = passwordEncoder.encode(rawPassword);
    primaryRequest.setPassword(encodedPassword);

    clientServiceRepository.save(clientServiceRootSchema);

    String email = customerProfile.getCustomerEmail();
    if (email == null || email.isEmpty()) {
      throw new IllegalArgumentException("Email cannot be null or empty");
    }

    AuthRequest authRequest = new AuthRequest(email);
    String emailFromAuthServiceClient = authServiceClient.registerCustomer(email, authRequest);
    ClientServiceRootResponse clientServiceRootResponse =
        ClientServiceRootResponse.builder().customerEmail(emailFromAuthServiceClient).build();

    return clientServiceRootResponse;
  }
}
