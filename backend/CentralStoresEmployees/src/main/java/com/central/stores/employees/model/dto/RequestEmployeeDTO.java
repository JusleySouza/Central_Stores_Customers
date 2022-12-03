package com.central.stores.employees.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestEmployeeDTO {

	private String name;
	private String cpf;
	private String rg;
	private String role;
	private String gender;
	private String phone;
	private String email;
}