package com.zapcom.openfeign;

import com.zapcom.model.AuthRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/** Created by Rama Gopal Project Name - client-service */
@FeignClient(name = "auth-service", url = "${auth-service.base-url}")
public interface AuthServiceClient {

  @PostMapping("/generateToken/{email}")
  String registerCustomer(@PathVariable("email") String email, @RequestBody AuthRequest request);
}
