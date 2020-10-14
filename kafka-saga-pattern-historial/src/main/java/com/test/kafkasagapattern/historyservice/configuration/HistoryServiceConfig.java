package com.test.kafkasagapattern.historyservice.configuration;

import com.test.kafkasagapattern.model.evt.HistoryEvent;
import com.test.kafkasagapattern.historyservice.eventhandlers.HistoryEventConsumerService;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HistoryServiceConfig {

    @Autowired
    private HistoryEventConsumerService consumerService;

    @Bean
    public Consumer<HistoryEvent> historyEventConsumer() {
        return consumerService::consumeHistoryEvent;
    }

}
