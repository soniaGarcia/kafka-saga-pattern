package com.test.kafkasagapattern.paymentservice.eventhandlers;

import com.test.kafkasagapattern.model.enums.HistoryStatus;
import com.test.kafkasagapattern.model.enums.PaymentStatus;
import com.test.kafkasagapattern.model.evt.HistoryEvent;
import com.test.kafkasagapattern.model.evt.OrderEvent;
import com.test.kafkasagapattern.model.evt.PaymentEvent;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.FluxSink;

@Service
public class HistoryEventPublisherService {

    @Autowired
    private FluxSink<HistoryEvent> historyEventChannel;

    public void raiseOrderCreatedEvent(final OrderEvent orderEvent) {

        HistoryEvent historyEvent = new HistoryEvent();
        historyEvent.setFecha(new Date());
        historyEvent.setPurchaseId(orderEvent.getOrderId());
        historyEvent.setStatus(HistoryStatus.CREDIT_VERIFICATION);

        this.historyEventChannel.next(historyEvent);
    }
    
    public void raiseOrderCreatedEvent(final PaymentEvent paymentEvent) {

        HistoryEvent historyEvent = new HistoryEvent();
        historyEvent.setFecha(new Date());
        historyEvent.setPurchaseId(paymentEvent.getOrderId());
        historyEvent.setStatus(paymentEvent.getStatus().equals(PaymentStatus.APPROVED) ? HistoryStatus.PAYMENT_APPROVED : HistoryStatus.PAYMENT_DECLINED);
        this.historyEventChannel.next(historyEvent);
    }
}
