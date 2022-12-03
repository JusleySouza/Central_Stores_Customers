package com.central.stores.employees.model.dto;

import java.util.UUID;

import com.central.stores.employees.model.Employee;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseEmployeeDTO {
	private UUID id;
	private String name;
	
	public void transformModelToResponseEmployeeDTO(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
	}
}
