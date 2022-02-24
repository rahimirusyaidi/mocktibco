package com.accenture.mocktibco.controller;


import com.accenture.mocktibco.model.ProfillingDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping(value = "/tibco")
public class TibcoMockController {

    @GetMapping(value = "/customer-account")
    public Object getCustomerProfile(@RequestParam String idValue) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("customer-account-response.json"), ProfillingDto.class);

    }
}
