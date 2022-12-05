package com.central.stores.customers.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestCustomerDTO {

	private String name;
	private String cpf;
	private String rg;
	private String gender;
	private String phone;
	private String email;

}
