package com.test.kafkasagapattern.historyservice.service;

import com.test.kafkasagapattern.historyservice.entity.PurchaseOrderHistory;
import com.test.kafkasagapattern.historyservice.repository.PurchaseOrderHistoryRepository;
import com.test.kafkasagapattern.model.dto.HistoryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryService {

    @Autowired
    private PurchaseOrderHistoryRepository purchaseOrderHistoryRepository;

    public List<HistoryResponseDTO> getAll() {
        return this.purchaseOrderHistoryRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    private HistoryResponseDTO entityToDto(final PurchaseOrderHistory purchaseOrderHistory) {
        HistoryResponseDTO dto = new HistoryResponseDTO();
        dto.setId(purchaseOrderHistory.getId());
        dto.setFecha(purchaseOrderHistory.getFecha());
        dto.setOrdenId(purchaseOrderHistory.getPurchaseId());
        dto.setStatus(purchaseOrderHistory.getStatus());
        return dto;
    }

}
