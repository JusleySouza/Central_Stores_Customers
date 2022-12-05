package com.central.stores.customers.model.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseCustomerDTO {

	private UUID id;
	private String name;

}
