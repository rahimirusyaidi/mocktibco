package com.accenture.mocktibco.repository;

import com.accenture.mocktibco.model.BillingDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BillingHistoryRepository extends CrudRepository<BillingDto, String> {

    Optional<BillingDto> findByBillNoAndBillingAccountNo(String billNo, String billingAccountNo);

    Optional<List<BillingDto>> findByBillingAccountNo(String billingAccountNo);
}
