package com.central.stores.employees.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDTO {
	
	private String street;
	private Integer number;
	private String neighborhood;
	private String city;
}