package com.accenture.mocktibco.controller;

import com.accenture.mocktibco.model.BillingDto;
import com.accenture.mocktibco.model.ProfillingDto;
import com.accenture.mocktibco.model.ServiceDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class SiebelMockController {

    private final RestTemplate restTemplate;

    public SiebelMockController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping(value = "/customer-account", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Object getCustomerProfile(@RequestParam String idValue) throws Exception {

        // simulate slow function - comment if necessary
        Thread.sleep(TimeUnit.MINUTES.toMillis(15));

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("customer-account-response.json"), ProfillingDto.class);
    }


    @GetMapping(value = "/billing-details", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Object getBillingProfile(@RequestParam String billingAccountNo) throws Exception {
        // simulate slow function - comment if necessary
        Thread.sleep(TimeUnit.MINUTES.toMillis(15));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("billing-account-response.json"), BillingDto.class);
    }


    @GetMapping(value = "/service-account", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Object getServiceProfile(@RequestParam String billingAccountNo) throws Exception {
        // simulate slow function - comment if necessary
        Thread.sleep(TimeUnit.MINUTES.toMillis(15));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("service-account-response.json"), ServiceDto.class);
    }

}
