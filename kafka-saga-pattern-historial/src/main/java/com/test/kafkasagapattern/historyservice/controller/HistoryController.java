package com.test.kafkasagapattern.historyservice.controller;

import com.test.kafkasagapattern.historyservice.service.HistoryService;
import com.test.kafkasagapattern.model.dto.HistoryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping("/all")
    public List<HistoryResponseDTO> getOrders(){
        return this.historyService.getAll();
    }

}
