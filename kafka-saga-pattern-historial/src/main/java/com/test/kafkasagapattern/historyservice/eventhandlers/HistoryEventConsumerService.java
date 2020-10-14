package com.test.kafkasagapattern.historyservice.eventhandlers;

import com.test.kafkasagapattern.model.evt.HistoryEvent;
import com.test.kafkasagapattern.historyservice.entity.PurchaseOrderHistory;
import com.test.kafkasagapattern.historyservice.repository.PurchaseOrderHistoryRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class HistoryEventConsumerService {

    @Autowired
    private PurchaseOrderHistoryRepository purchaseOrderHistoryRepository;

    public void consumeHistoryEvent(HistoryEvent historyEvent) {

        PurchaseOrderHistory historial = new PurchaseOrderHistory();
        historial.setFecha(historyEvent.getFecha());
        historial.setPurchaseId(historyEvent.getPurchaseId());
        historial.setStatus(historyEvent.getStatus());
        this.purchaseOrderHistoryRepository.save(historial);

    }

}
