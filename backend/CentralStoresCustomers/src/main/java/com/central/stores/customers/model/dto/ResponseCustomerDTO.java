package com.central.stores.customers.model.dto;

import java.util.UUID;

import com.central.stores.customers.model.Customer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseCustomerDTO {

	private UUID id;
	private String name;
	
	public void transformModelToResponseCustomerDTO(Customer customer) {
		this.id = customer.getId();
		this.name = customer.getName();
	}

}
