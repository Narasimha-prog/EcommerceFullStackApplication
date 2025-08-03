package com.lnr.ecom.order.infrastrature.secondary.service.kinde;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class KindeService {

  private final String apiUrl;
  private final String clientId;
  private final String clientSecret;
  private final String audience;
  private final RestClient restClient;

  public KindeService(
    @Value("${application.kinde.api}") String apiUrl,
    @Value("${application.kinde.client-id}") String clientId,
    @Value("${application.kinde.client-secret}") String clientSecret,
    @Value("${application.kinde.audience}") String audience) {

    this.apiUrl = apiUrl;
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.audience = audience;
    this.restClient = RestClient.builder()
      .requestFactory(new HttpComponentsClientHttpRequestFactory())
      .baseUrl(apiUrl)
      .build();
  }

  private Optional<String> getToken() {
    try {
      MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
      form.add("grant_type", "client_credentials");
      form.add("client_id", clientId);
      form.add("client_secret", clientSecret);
      form.add("audience", audience);

      ResponseEntity<KindeAccessToken> accessTokenResponse = restClient.post()
        .uri("/oauth/token")
        .body(form)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .retrieve()
        .toEntity(KindeAccessToken.class);

      if (accessTokenResponse.getBody() == null || accessTokenResponse.getBody().accessToken() == null) {
        log.error("No access token returned from Kinde");
        return Optional.empty();
      }

      String token = accessTokenResponse.getBody().accessToken();


      return Optional.of(token);
    } catch (Exception e) {
      log.error("Error while getting token from Kinde:", e);
      return Optional.empty();
    }
  }

  public Map<String, Object> getUserInfo(String userId) {
    String token = getToken().orElseThrow(() -> new IllegalStateException("Token not found"));

    var typeRef = new ParameterizedTypeReference<Map<String, Object>>() {
    };

    ResponseEntity<Map<String, Object>> response = restClient.get()
      // Adjust URL based on Kinde documentation. Some APIs use /users/{id} instead.
      .uri("/api/v1/user?id={id}", userId)
      .header("Authorization", "Bearer " + token)
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .toEntity(typeRef);

    return response.getBody();
  }
}
