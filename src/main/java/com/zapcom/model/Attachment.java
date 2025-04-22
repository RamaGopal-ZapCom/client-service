package com.zapcom.model;

import lombok.Builder;
import lombok.Data;

/** Created by Rama Gopal Project Name - client-service */
@Data
@Builder
public class Attachment {
  private String file_name;
  private String file_type;
  private String file_data;
  private String file_encoding_type;
}
