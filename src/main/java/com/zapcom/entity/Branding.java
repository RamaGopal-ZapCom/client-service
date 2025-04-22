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
public class Branding {
    public String logoUrl;
    public String brandName;
    public String greetingMessage;
    public String fallBackResponse;
}
