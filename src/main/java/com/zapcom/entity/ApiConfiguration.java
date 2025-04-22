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
public class ApiConfiguration {

    public int estimatedMonthlyRequests;
    public int requestsPerMinute;
    public String peakUsageHours;
    public String botPurpose;
    public ArrayList<String> complianceStandards;

}
