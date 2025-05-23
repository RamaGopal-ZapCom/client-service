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
public class Staff {
  public int kitchenStaff;
  public int deliveryStaff;
}
