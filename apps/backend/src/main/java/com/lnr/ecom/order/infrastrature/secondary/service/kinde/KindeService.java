package com.lnr.ecom.order.infrastrature.secondary.service.kinde;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;
@Slf4j
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

  private Optional<String> getToken(){
    try{
       ResponseEntity<KindeAccessToken> accessToken= restClient.post()
         .uri(URI.create("/oauth/token"))
         .body("grant_type=client_credentials&audience=" + URLEncoder.encode(audience, StandardCharsets.UTF_8))
         .accept(MediaType.APPLICATION_JSON)
         .contentType(MediaType.APPLICATION_FORM_URLENCODED)
         .header("Authorization",
           "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8)))
         .header("Content-Type", ContentType.APPLICATION_FORM_URLENCODED.getMimeType())
         .retrieve()
         .toEntity(KindeAccessToken.class);


      return Optional.of(accessToken.getBody().accessToken());

    } catch (Exception e) {
     log.error("While Getting Token From Kinde :",e);
     return Optional.empty();
    }
  }

  public Map<String,Object> getUserInfo(String userId){
   String token= getToken().orElseThrow(()->new IllegalStateException("Token Not Found"));


   var typeRef= new ParameterizedTypeReference<Map<String,Object>>(){};

 ResponseEntity<Map<String,Object>> authorization =  restClient.get()
     .uri(apiUrl+"/api/v1/user?id={id}",userId)
     .header("Authorization","Bearer "+token)
     .accept(MediaType.APPLICATION_JSON)
     .retrieve()
     .toEntity(typeRef);


   return authorization.getBody();
  }
}
