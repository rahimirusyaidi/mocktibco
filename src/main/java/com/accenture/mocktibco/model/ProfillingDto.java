package com.accenture.mocktibco.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProfillingDto {

    @JsonProperty("name")
    private String name;
    @JsonProperty("idValue")
    private String idValue;
    @JsonProperty("accountNo")
    private String accountNo;
    @JsonProperty("billingAccount")
    private Integer billingAccount;
    @JsonProperty("phoneNumber")
    private Long phoneNumber;
    @JsonProperty("email")
    private String email;
}
