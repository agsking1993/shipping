package com.microservice.shipping.service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.shipping.entity.ShippingInfo;
import com.microservice.shipping.repository.ShippingInfoRepository;
import com.util.commons.dto.OrderRequestDto;
import com.util.commons.dto.ShippingRequestDto;
import com.util.commons.event.OrderEvent;
import com.util.commons.event.ShippingEvent;
import com.util.commons.status.ShippingStatus;

@Service
public class ShippingService {

    @Autowired
    private ShippingInfoRepository shippingInfoRepository;
     

    @PostConstruct
    public void initUserBalanceInDB() {
        shippingInfoRepository.saveAll(Stream.of(new ShippingInfo(101, 10,110100),
                new ShippingInfo(102, 20,111221),
                new ShippingInfo(103, 30,111071),
                new ShippingInfo(104, 40,110821),
                new ShippingInfo(105, 50,110411)).collect(Collectors.toList()));
    }


    @Transactional
    public ShippingEvent newShippingEvent(OrderEvent orderEvent) {
        OrderRequestDto orderRequestDto = orderEvent.getOrderRequestDto();

    	
        ShippingRequestDto shippingRequestDto = new ShippingRequestDto(orderRequestDto.getProductId(), orderRequestDto.getUserId(), orderRequestDto.getPostalCode(), orderRequestDto.getOrderId());


        return shippingInfoRepository.findById(shippingRequestDto.getUserId())
                .filter(ub -> ub.getProductId() ==  shippingRequestDto.getProductId() && ub.getIdPostalCode() ==  shippingRequestDto.getPostalCode())
                .map(ub -> {
                	shippingInfoRepository.save(new ShippingInfo(shippingRequestDto.getProductId(), shippingRequestDto.getUserId(), shippingRequestDto.getPostalCode()));
                    return new ShippingEvent(shippingRequestDto, ShippingStatus.SHIPPING_COMPLETED);
                }).orElse(new ShippingEvent(shippingRequestDto, ShippingStatus.SHIPPING_FAILED));
    }

    @Transactional
    public void cancelOrderEvent(OrderEvent shippingRequestDto) {

    	shippingInfoRepository.findById(shippingRequestDto.getOrderRequestDto().getUserId())
                .ifPresent(ut->{
                	shippingInfoRepository.delete(ut);                	 
                });
    }
}
