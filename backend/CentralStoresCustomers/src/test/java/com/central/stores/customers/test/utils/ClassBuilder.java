package com.central.stores.customers.test.utils;

import java.time.LocalDate;
import java.util.UUID;

import com.central.stores.customers.model.Address;
import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.AddressDTO;
import com.central.stores.customers.model.dto.RequestCustomerDTO;
import com.central.stores.customers.model.dto.ResponseCustomerDTO;

public final class ClassBuilder {

	public static Address addressBuilder() {
		return Address.builder().city("Bauru").neighborhood("Pedro Santos Drumond").number(56).street("Rua das Amelias")
				.changed(LocalDate.now()).created(LocalDate.now()).build();
	}

	public static AddressDTO addressDTOBuider() {
		return AddressDTO.builder().city("Bauru").neighborhood("Pedro Santos Drumond").number(56)
				.street("Rua das Amelias").build();
	}

	public static Customer customerBuider() {
		return Customer.builder().active(true).changed(LocalDate.now()).cpf("12365478965").created(LocalDate.now())
				.email("caio@castro.com").gender("masculino").name("Caio Castro").phone("1111111111").rg("325698741")
				.build();
	}
	
	public static RequestCustomerDTO requestCustomerDTOBuider() {
		return RequestCustomerDTO.builder().cpf("12365478965").email("caio@castro.com").gender("masculino")
				.name("Caio Castro").phone("1111111111").rg("325698741").build();
	}
	
	public static ResponseCustomerDTO responseCustomerDTOBuider() {
		return ResponseCustomerDTO.builder().name("Caio Castro").id(UUID.randomUUID()).build();
	}
	
}
