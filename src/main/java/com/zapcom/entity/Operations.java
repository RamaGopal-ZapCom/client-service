package com.zapcom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * Created by Rama Gopal
 * Project Name - client-service
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Operations {
    public OperatingHours operatingHours;
    public ArrayList<String> cuisineType;
    public String deliveryRadiusPreference;
    public Staff staff;
    public ArrayList<String> websiteOrSocialMediaLinks;
}
