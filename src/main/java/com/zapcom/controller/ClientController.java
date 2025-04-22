package com.zapcom.controller;

import com.zapcom.entity.ClientSchema;
import com.zapcom.model.request.ClientServiceRootRequest;
import com.zapcom.model.response.ClientResponse;
import com.zapcom.model.response.ClientServiceRootResponse;
import com.zapcom.service.ClientService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Created by Rama Gopal Project Name - client-service */
@RestController
@RequestMapping("${application.path}")
@AllArgsConstructor
public class ClientController {

  private final ClientService customerService;

  @Operation(
      summary = "Register a new client",
      description = "This API registers a new client and requires an X-Client-Token header")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Client registered successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request format"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  @PostMapping("/register")
  public ResponseEntity<?> register(
      @Parameter(
              description = "API token provided by the client",
              required = true,
              example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
          @RequestHeader("X-Client-Token")
          String token,
      @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "Client registration details",
              required = true,
              content = @Content(schema = @Schema(implementation = ClientSchema.class)))
          @RequestBody
          ClientSchema customer) {

    // You can log or validate token here if needed

    String message = customerService.registerCustomer(customer);
    return new ResponseEntity<>(message, HttpStatus.CREATED);
  }

  /*  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody ClientSchema customer) {
    String message = customerService.registerCustomer(customer);
    return new ResponseEntity<>(message, HttpStatus.CREATED);
  }*/

  @Hidden
  @PostMapping("/reset-token")
  public ResponseEntity<String> resetToken(@RequestParam String email) {
    String message = customerService.resetToken(email);
    return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
  }

  @Hidden
  @GetMapping("/{email}")
  public ResponseEntity<ClientResponse> getCustomerByEmail(@PathVariable("email") String email) {
    ClientResponse customerResponse = customerService.getCustomerByEmail(email);
    return new ResponseEntity<>(customerResponse, HttpStatus.OK);
  }

  @Hidden
  @PostMapping("/clientRegister")
  public ResponseEntity<?> clientRegister(@RequestBody ClientServiceRootRequest clientRootRequest) {
    ClientServiceRootResponse response = customerService.clientRegister(clientRootRequest);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
