package com.test.kafkasagapattern.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.test.kafkasagapattern.historyservice.service.HistoryService;
import com.test.kafkasagapattern.model.dto.HistoryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryQuery implements GraphQLQueryResolver {

    @Autowired
    private HistoryService historyService;

    public List<HistoryResponseDTO> getHistorial() {
        return this.historyService.getAll();
    }


}
