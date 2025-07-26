package com.lnr.ecom.order.infrastrature.secondaryservice.kinde;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class KindeService {

  @Value("${application.kinde.api}")
  private String apiUrl;

  @Value("${application.kinde.client-id}")
  private String clientId;

  @Value("${application.kinde.client-secret}")
  private String clientSecret;

  @Value("${application.kinde.audience}")
  private String audience;



  private final RestClient restClient=RestClient.builder()
    .requestFactory(new HttpComponentsClientHttpRequestFactory())
    .baseUrl(apiUrl)
    .build();
}
