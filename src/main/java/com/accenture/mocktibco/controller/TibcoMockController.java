package com.accenture.mocktibco.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
        String url = "http://localhost:8081/siebel/profile?billingAccountNo=" + idValue;
        return restTemplate.getForObject(url, Object.class);
    }


    @GetMapping(value = "/billing-details", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Cacheable(cacheNames = "billing", key = "{#billingAccountNo}", unless = "#result == null")
    public Object getBillingProfile(@RequestParam String billingAccountNo) throws Exception {
        String url = "http://localhost:8081/siebel/billing?billingAccountNo=" + billingAccountNo;
        return restTemplate.getForObject(url, Object.class);

    }


    @GetMapping(value = "/service-account", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Cacheable(cacheNames = "service", key = "{#billingAccountNo}", unless = "#result == null")
    public Object getServiceProfile(@RequestParam String billingAccountNo) throws Exception {
        String url = "http://localhost:8081/siebel/service?billingAccountNo=" + billingAccountNo;
        return restTemplate.getForObject(url, Object.class);
    }
}
