package com.test.kafkasagapattern.orderservice.service;

import com.test.kafkasagapattern.model.dto.OrderRequestDTO;
import com.test.kafkasagapattern.model.dto.OrderResponseDTO;
import com.test.kafkasagapattern.model.enums.OrderStatus;
import com.test.kafkasagapattern.orderservice.entity.PurchaseOrder;
import com.test.kafkasagapattern.orderservice.eventhandlers.HistoryEventPublisherService;
import com.test.kafkasagapattern.orderservice.eventhandlers.OrderEventPublisherService;
import com.test.kafkasagapattern.orderservice.repository.PurchaseOrderRepository;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    // product price map
    private static final Map<Integer, Integer> PRODUCT_PRICE = new HashMap<Integer, Integer>() {
        {
            put(1, 100);
            put(2, 200);
            put(3, 00);
        }
    };

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private OrderEventPublisherService eventPublisherService;

    @Autowired
    private HistoryEventPublisherService historyEventPublisherService;

    public PurchaseOrder createOrder(OrderRequestDTO orderRequestDTO) {
        PurchaseOrder purchaseOrder = this.purchaseOrderRepository.save(this.dtoToEntity(orderRequestDTO));
        this.eventPublisherService.raiseOrderCreatedEvent(purchaseOrder);
        this.historyEventPublisherService.raiseOrderCreatedEvent(purchaseOrder);
        return purchaseOrder;
    }

    public List<OrderResponseDTO> getAll() {
        return this.purchaseOrderRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    private PurchaseOrder dtoToEntity(final OrderRequestDTO dto) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(dto.getProductId());
        purchaseOrder.setUserId(dto.getUserId());
        purchaseOrder.setStatus(OrderStatus.ORDER_CREATED);
        purchaseOrder.setPrice(PRODUCT_PRICE.get(purchaseOrder.getProductId()));
        return purchaseOrder;
    }

    private OrderResponseDTO entityToDto(final PurchaseOrder purchaseOrder) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(purchaseOrder.getId());
        dto.setProductId(purchaseOrder.getProductId());
        dto.setUserId(purchaseOrder.getUserId());
        dto.setStatus(purchaseOrder.getStatus());
        dto.setPrice(purchaseOrder.getPrice());
        return dto;
    }

}
