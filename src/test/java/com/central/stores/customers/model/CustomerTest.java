package com.central.stores.customers.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.central.stores.customers.test.utils.ClassBuilder;

class CustomerTest {
	
	private Customer expectedCustomer;
	
	private UUID id;
	
	private Address address;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		address = ClassBuilder.addressBuilder();
		expectedCustomer = ClassBuilder.customerBuilder();
		expectedCustomer.setId(id);
		expectedCustomer.setAddress(address);
	}
	
	@Test
	void builder() {
		Customer customer = Customer.builder()
				.active(true)
				.changed(LocalDate.now())
				.cpf("12365478965")
				.created(LocalDate.now())
				.email("caio@castro.com")
				.gender("masculino")
				.name("Caio Castro")
				.phone("1111111111")
				.rg("325698741")
				.id(id)
				.address(address)
				.build();
		assertEquals(expectedCustomer.toString(), customer.toString());

	}

	@Test
	void setter() {
		Customer customer = new Customer();
		customer.setActive(true);
		customer.setChanged(LocalDate.now());
		customer.setCpf("12365478965");
		customer.setCreated(LocalDate.now());
		customer.setEmail("caio@castro.com");
		customer.setGender("masculino");
		customer.setName("Caio Castro");
		customer.setPhone("1111111111");
		customer.setRg("325698741");
		customer.setId(id);
		customer.setAddress(address);
		assertEquals(expectedCustomer.toString(), customer.toString());
	}

}
