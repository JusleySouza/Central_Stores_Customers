package com.central.stores.customers.model.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDTO {

	private UUID id;
	private String street;
	private Integer number;
	private String neighborhood;
	private String city;

}
