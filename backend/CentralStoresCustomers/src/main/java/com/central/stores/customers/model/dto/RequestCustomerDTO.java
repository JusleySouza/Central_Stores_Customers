package com.central.stores.customers.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCustomerDTO {

	private String name;
	private String cpf;
	private String rg;
	private String gender;
	private String phone;
	private String email;

}
