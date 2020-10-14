package com.test.kafkasagapattern.orderservice.eventhandlers;

import com.test.kafkasagapattern.model.enums.HistoryStatus;
import com.test.kafkasagapattern.model.enums.PaymentStatus;
import com.test.kafkasagapattern.model.evt.HistoryEvent;
import com.test.kafkasagapattern.model.evt.PaymentEvent;
import com.test.kafkasagapattern.orderservice.entity.PurchaseOrder;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.FluxSink;

@Service
public class HistoryEventPublisherService {

    @Autowired
    private FluxSink<HistoryEvent> historyEventChannel;

    public void raiseOrderCreatedEvent(final PurchaseOrder purchaseOrder) {

        HistoryEvent historyEvent = new HistoryEvent();
        historyEvent.setFecha(new Date());
        historyEvent.setPurchaseId(purchaseOrder.getId());
        historyEvent.setStatus(HistoryStatus.ORDER_CREATED);

        this.historyEventChannel.next(historyEvent);
    }
    
    public void raiseOrderCreatedEvent(final PaymentEvent paymentEvent) {

        HistoryEvent historyEvent = new HistoryEvent();
        historyEvent.setFecha(new Date());
        historyEvent.setPurchaseId(paymentEvent.getOrderId());
        historyEvent.setStatus(paymentEvent.getStatus().equals(PaymentStatus.APPROVED) ? HistoryStatus.ORDER_COMPLETED : HistoryStatus.ORDER_CANCELLED);

        this.historyEventChannel.next(historyEvent);
    }
}
