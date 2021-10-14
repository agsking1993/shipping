package com.microservice.shipping.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingInfo {
    
	@Id
    private int userId;
    private int productId;
    private int idPostalCode;
}
