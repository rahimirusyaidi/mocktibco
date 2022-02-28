DROP TABLE IF EXISTS BILLING_HISTORY;

CREATE TABLE BILLING_HISTORY
(
    BILL_NO            VARCHAR PRIMARY KEY,
    BILLING_ACCOUNT_NO VARCHAR (50) NOT NULL,
    OUTSTANDING_AMOUNT DOUBLE NOT NULL,
    DUE_DATE           VARCHAR(50) DEFAULT NULL,
    UPDATED_AT         VARCHAR(50) DEFAULT NULL
);

INSERT INTO BILLING_HISTORY (BILL_NO, BILLING_ACCOUNT_NO, OUTSTANDING_AMOUNT, DUE_DATE, UPDATED_AT) VALUES ('12ASD', '1233211', '102.20', '20/02/2022', '19/02/2022');
INSERT INTO BILLING_HISTORY (BILL_NO, BILLING_ACCOUNT_NO, OUTSTANDING_AMOUNT, DUE_DATE, UPDATED_AT) VALUES ('123ASD', '1233211', '102.20', '20/01/2022', '19/01/2022');
INSERT INTO BILLING_HISTORY (BILL_NO, BILLING_ACCOUNT_NO, OUTSTANDING_AMOUNT, DUE_DATE, UPDATED_AT) VALUES ('1234ASD', '1233211', '102.20', '20/12/2021', '19/01/2021');
