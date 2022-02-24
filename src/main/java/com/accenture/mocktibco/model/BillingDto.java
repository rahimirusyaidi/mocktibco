package com.accenture.mocktibco.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BillingDto {

    Integer billingAccountNo;

    Double outstandingAmount;

    String dueDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    Date updateAt;
}
