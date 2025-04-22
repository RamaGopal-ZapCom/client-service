package com.zapcom.entity;

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
public class ClientProfile {
    public String customerName;
    public String industryType;
    public String businessCategory;
    public RegisteredAddress registeredAddress;
    public String country;
    public String gstNumber;
    public String customerWebsite;
    public String customerRegistrationNumber;
    public String ownerName;
    public String contactNumber;
    public String customerEmail;
}
