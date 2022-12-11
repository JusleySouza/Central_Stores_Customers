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

	
}
