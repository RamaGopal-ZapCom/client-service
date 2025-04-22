package com.zapcom.entity;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Created by Rama Gopal Project Name - client-service */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatbotConfiguration {
  public Theme theme;
  public ArrayList<String> supportedLanguages;
  public String chatWidgetPosition;
}
