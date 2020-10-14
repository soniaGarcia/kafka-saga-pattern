package com.test.kafkasagapattern.orderservice.eventhandlers;

import com.test.kafkasagapattern.model.enums.OrderStatus;
import com.test.kafkasagapattern.model.evt.PaymentEvent;
import com.test.kafkasagapattern.model.enums.PaymentStatus;
import com.test.kafkasagapattern.orderservice.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PaymentEventConsumerService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    
    @Autowired
    private HistoryEventPublisherService historyEventPublisherService;

    @Transactional
    public void consumePaymentEvent(PaymentEvent paymentEvent){
        this.purchaseOrderRepository.findById(paymentEvent.getOrderId())
                    .ifPresent(purchaseOrder -> {
                        purchaseOrder.setStatus(paymentEvent.getStatus().equals(PaymentStatus.APPROVED) ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED);
                        this.purchaseOrderRepository.save(purchaseOrder);
                    });
        
        historyEventPublisherService.raiseOrderCreatedEvent(paymentEvent);
    }

}
