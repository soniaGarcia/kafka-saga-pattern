package com.test.kafkasagapattern.historyservice.repository;

import com.test.kafkasagapattern.historyservice.entity.PurchaseOrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderHistoryRepository extends JpaRepository<PurchaseOrderHistory, Integer> {
}
