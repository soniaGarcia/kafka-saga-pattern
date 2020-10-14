package com.test.kafkasagapattern.orderservice.eventhandlers;

import com.test.kafkasagapattern.model.evt.OrderEvent;
import com.test.kafkasagapattern.orderservice.entity.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.FluxSink;

@Service
public class OrderEventPublisherService {

    @Autowired
    private FluxSink<OrderEvent> orderEventChannel;


    public void raiseOrderCreatedEvent(final PurchaseOrder purchaseOrder) {
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setUserId(purchaseOrder.getUserId());
        orderEvent.setPrice(purchaseOrder.getPrice());
        orderEvent.setOrderId(purchaseOrder.getId());
        this.orderEventChannel.next(orderEvent);
    }
}
