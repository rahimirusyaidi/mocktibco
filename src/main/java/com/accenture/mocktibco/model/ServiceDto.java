package com.accenture.mocktibco.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ServiceDto {

    String billingAccountNo;

    List<SubscribedService> subscribedServiceList;

    @Data
    private static class SubscribedService{

        String planName;

        String status;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        Date activationDate;
    }

}


