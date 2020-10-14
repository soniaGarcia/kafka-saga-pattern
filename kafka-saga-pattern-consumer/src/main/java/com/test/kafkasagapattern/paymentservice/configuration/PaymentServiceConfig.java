package com.test.kafkasagapattern.paymentservice.configuration;

import com.test.kafkasagapattern.model.evt.HistoryEvent;
import com.test.kafkasagapattern.model.evt.OrderEvent;
import com.test.kafkasagapattern.model.evt.PaymentEvent;
import com.test.kafkasagapattern.paymentservice.eventhandlers.OrderEventProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;
import java.util.function.Supplier;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Configuration
public class PaymentServiceConfig {

    @Autowired
    private OrderEventProcessorService orderEventProcessorService;

    @Bean
    public Function<OrderEvent, PaymentEvent> orderEventProcessor() {
        return orderEventProcessorService::processOrderEvent;
    }

    @Bean
    public DirectProcessor<HistoryEvent> getFlux() {
        return DirectProcessor.create();
    }

    @Bean
    public FluxSink<HistoryEvent> historyEventChannel(DirectProcessor<HistoryEvent> processor) {
        return processor.sink();
    }

    @Bean
    public Supplier<Flux<HistoryEvent>> historyEventPublisher(DirectProcessor<HistoryEvent> processor) {
        return () -> processor;
    }
}
