package com.lnr.ecom.order.infrastrature.secondary.service.kinde;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KindeAccessToken
          (@JsonProperty("access_token") String accessToken
            ,@JsonProperty("token_type") String tokenType) {
}
