package com.accenture.mocktibco.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BILLING_HISTORY")
@Setter
@Getter
public class BillingDto {

    @Id
    @Column(name = "BILL_NO")
    private String billNo;
    @Column(name = "BILLING_ACCOUNT_NO")
    private String billingAccountNo;
    @Column(name = "OUTSTANDING_AMOUNT")
    private Double outstandingAmount;
    @Column(name = "DUE_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String dueDate;
    @Column(name = "UPDATED_AT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private String updateAt;
}
