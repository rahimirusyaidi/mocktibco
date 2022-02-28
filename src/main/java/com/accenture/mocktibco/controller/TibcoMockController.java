package com.accenture.mocktibco.controller;


import com.accenture.mocktibco.model.BillingDto;
import com.accenture.mocktibco.repository.BillingHistoryRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/tibco")
public class TibcoMockController {

    private final RestTemplate restTemplate;

    private final BillingHistoryRepository billingHistoryRepository;

    public TibcoMockController(RestTemplateBuilder restTemplateBuilder, BillingHistoryRepository billingHistoryRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.billingHistoryRepository = billingHistoryRepository;
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

    @GetMapping(value = "/update-outstanding-amount")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateBillingAmount(@RequestParam String billNo, String billingAccountNo, Double amountPaid) throws Exception {
        Optional<BillingDto> result = billingHistoryRepository.findByBillNoAndBillingAccountNo(billNo, billingAccountNo);
        result.ifPresent(billingDto -> {
            billingDto.setOutstandingAmount(amountPaid);
            billingHistoryRepository.save(billingDto);
        });
    }
}
