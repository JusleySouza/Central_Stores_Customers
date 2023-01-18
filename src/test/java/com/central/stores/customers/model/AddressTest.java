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
		expectedAddress.setId(id);
	}
	
	@Test
	void build() {
		Address address = Address.builder()
				.city("Bauru")
				.neighborhood("Pedro Santos Drumond")
				.number(56)
				.street("Rua das Amelias")
				.changed(LocalDate.now())
				.created(LocalDate.now())
				.id(id)
				.build();
		assertEquals(expectedAddress.toString(), address.toString());
	}

	@Test
	void setter() {
		Address address = new Address();
		address.setCity("Bauru");
		address.setNeighborhood("Pedro Santos Drumond");
		address.setNumber(56);
		address.setStreet("Rua das Amelias");
		address.setChanged(LocalDate.now());
		address.setCreated(LocalDate.now());
		address.setId(id);
		assertEquals(expectedAddress.toString(), address.toString());
	}

}
