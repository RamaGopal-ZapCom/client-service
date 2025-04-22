package com.zapcom.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Created by Rama Gopal Project Name - client-service */@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Address {
  @Schema(description = "Street address", example = "123, Main Street")
  private String street;

  @Schema(description = "City name", example = "Hyderabad")
  private String city;

  @Schema(description = "State name", example = "Telangana")
  private String state;

  @Schema(description = "Postal code", example = "500081")
  private String pincode;
}
