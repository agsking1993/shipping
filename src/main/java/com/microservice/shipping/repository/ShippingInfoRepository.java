package com.microservice.shipping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.shipping.entity.ShippingInfo;

public interface ShippingInfoRepository extends JpaRepository<ShippingInfo,Integer> {
	
}
