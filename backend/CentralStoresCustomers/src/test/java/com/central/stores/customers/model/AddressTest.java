package com.central.stores.customers.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.central.stores.customers.test.utils.ClassBuilder;

class AddressTest {
	
	private Address expectedAddress;
	private UUID id;
	
	@BeforeEach
	void setUp() {
		id = UUID.randomUUID();
		expectedAddress = ClassBuilder.addressBuilder();
	}

	@Test
	void build() {
		Address address = Address.builder().city("Bauru").neighborhood("Pedro Santos Drumond").number(56).street("Rua das Amelias")
				.changed(LocalDate.now()).created(LocalDate.now()).build();
		assertEquals(expectedAddress.toString(), address.toString());
	}

}
