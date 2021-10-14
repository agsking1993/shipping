package com.microservice.shipping.config;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservice.shipping.service.ShippingService;
import com.util.commons.event.OrderEvent;
import com.util.commons.event.ShippingEvent;
import com.util.commons.status.OrderStatus;
import com.util.commons.status.ShippingStatus;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class ShippingConsumerConfig {

    @Autowired
    private ShippingService shippingService;

    @Bean
    public Function<Flux<OrderEvent>, Flux<ShippingEvent>> shippingProcessor() {
        return orderEventFlux -> orderEventFlux.flatMap(this::processShipping);
    }

    private Mono<ShippingEvent> processShipping(OrderEvent orderEvent) {

        if(OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())){
            return  Mono.fromSupplier(()->this.shippingService.newShippingEvent(orderEvent));
        }else{
            return Mono.fromRunnable(()->this.shippingService.cancelOrderEvent(orderEvent));
        }
    }
}
