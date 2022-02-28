package com.accenture.mocktibco.controller;

import com.accenture.mocktibco.model.BillingDto;
import com.accenture.mocktibco.model.ProfillingDto;
import com.accenture.mocktibco.model.ServiceDto;
import com.accenture.mocktibco.repository.BillingHistoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.io.File;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/siebel")
@RequiredArgsConstructor
public class SiebelMockController {

    private final ObjectMapper objectMapper;

    private final BillingHistoryRepository repository;

    @GetMapping(value = "/customer-account")
    public Object getCustomerProfile(@RequestParam String idValue) throws Exception {

        // simulate slow function - comment if necessary
//        Thread.sleep(TimeUnit.MINUTES.toMillis(15));

        ProfillingDto response = objectMapper.readValue(new File("customer-account-response.json"), ProfillingDto.class);

        if (!response.getIdValue().equals(idValue))
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "account not found");

        return response;
    }


    @GetMapping(value = "/billing-details", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Object getBillingProfile(@RequestParam String billingAccountNo) throws Exception {

        // simulate slow function - comment if necessary
//        Thread.sleep(TimeUnit.MINUTES.toMillis(15));

        Optional<List<BillingDto>> result = repository.findByBillingAccountNo(billingAccountNo);
        if (result.isEmpty())
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "billingAccountNo not found");

        return result.get();
    }


    @GetMapping(value = "/service-account", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Object getServiceProfile(@RequestParam String billingAccountNo) throws Exception {

        // simulate slow function - comment if necessary
//        Thread.sleep(TimeUnit.MINUTES.toMillis(15));

        ServiceDto response = objectMapper.readValue(new File("service-account-response.json"), ServiceDto.class);

        if (!response.getBillingAccountNo().equals(billingAccountNo))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "billingAccountNo not found");

        return response;
    }

}
