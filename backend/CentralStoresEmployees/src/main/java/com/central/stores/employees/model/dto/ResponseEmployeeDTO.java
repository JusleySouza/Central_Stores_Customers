package com.central.stores.employees.model.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseEmployeeDTO {
	private UUID id;
	private String name;
}
