package com.zapcom.model;

import lombok.Builder;
import lombok.Data;

/** Created by Rama Gopal Project Name - client-service */
@Data
@Builder
public class Email {
  private String to;
  private String cc;
  private String bcc;
  private String from;
  private String subject;
  private String body;
  private Attachments attachments;
  private String scheduled_time;
}
