package com.accenture.mocktibco.controller;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
@RequestMapping(value = "/tibco")
public class TibcoMockController {

    private final RestTemplate restTemplate;

    public TibcoMockController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping(value = "/customer-account", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Cacheable(cacheNames = "profile", key = "{#idValue}", unless = "#result == null")
    public Object getCustomerProfile(@RequestParam String idValue) throws Exception {
        String url = "http://localhost:8081/siebel/customer-account?idValue=" + idValue;
        Object response;
        try {
            response = restTemplate.getForObject(url, Object.class);
        } catch (HttpStatusCodeException e) {
            throw new HttpClientErrorException(e.getStatusCode(), Objects.requireNonNull(e.getMessage()));
        }
        return response;
    }


    @GetMapping(value = "/billing-details", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Cacheable(cacheNames = "billing", key = "{#billingAccountNo}", unless = "#result == null")
    public Object getBillingProfile(@RequestParam String billingAccountNo) throws Exception {
        String url = "http://localhost:8081/siebel/billing-details?billingAccountNo=" + billingAccountNo;
        Object response;

        try {
            response = restTemplate.getForObject(url, Object.class);
        } catch (HttpStatusCodeException e) {
            throw new HttpClientErrorException(e.getStatusCode(), Objects.requireNonNull(e.getMessage()));
        }
        return response;

    }


    @GetMapping(value = "/service-account", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Cacheable(cacheNames = "service", key = "{#billingAccountNo}", unless = "#result == null")
    public Object getServiceProfile(@RequestParam String billingAccountNo) throws Exception {
        String url = "http://localhost:8081/siebel/service-account?billingAccountNo=" + billingAccountNo;
        Object response;
        try {
            response = restTemplate.getForObject(url, Object.class);
        } catch (HttpStatusCodeException e) {
            throw new HttpClientErrorException(e.getStatusCode(), Objects.requireNonNull(e.getMessage()));
        }
        return response;
    }
}
