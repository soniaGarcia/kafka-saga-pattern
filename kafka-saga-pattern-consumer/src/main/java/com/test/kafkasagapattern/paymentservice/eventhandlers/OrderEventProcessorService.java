package com.test.kafkasagapattern.paymentservice.eventhandlers;

import com.test.kafkasagapattern.model.enums.PaymentStatus;
import com.test.kafkasagapattern.model.evt.OrderEvent;
import com.test.kafkasagapattern.model.evt.PaymentEvent;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OrderEventProcessorService {

    // user - credit limit
    public static final Map<Integer, Integer> userMap = new HashMap<>();

    @Autowired
    private HistoryEventPublisherService historyEventPublisherService;

    static {
        userMap.put(1, 1000);
        userMap.put(2, 1000);
        userMap.put(3, 1000);
        userMap.put(4, 1000);
        userMap.put(5, 1000);
    }

    public PaymentEvent processOrderEvent(OrderEvent orderEvent) {
        historyEventPublisherService.raiseOrderCreatedEvent(orderEvent);
        Integer price = orderEvent.getPrice();
        Integer creditLimit = userMap.get(orderEvent.getUserId());
        PaymentEvent paymentEvent = new PaymentEvent(orderEvent.getOrderId());
        if (creditLimit >= price) {
            paymentEvent.setStatus(PaymentStatus.APPROVED);
            userMap.computeIfPresent(orderEvent.getUserId(), (k, v) -> v - price);
        } else {
            paymentEvent.setStatus(PaymentStatus.REJECTED);
        }
        historyEventPublisherService.raiseOrderCreatedEvent(paymentEvent);
        return paymentEvent;
    }

}
