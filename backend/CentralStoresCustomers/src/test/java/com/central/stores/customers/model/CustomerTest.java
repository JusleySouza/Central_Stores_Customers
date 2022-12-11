package com.central.stores.customers.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.central.stores.customers.test.utils.ClassBuilder;

class CustomerTest {
	
	private Customer expectedCustomer;
	
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedCustomer = ClassBuilder.customerBuider();
		expectedCustomer.setId(id);
	}

	@Test
	void builder() {
		Customer customer = Customer.builder().active(true).changed(LocalDate.now()).cpf("12365478965").created(LocalDate.now())
				.email("caio@castro.com").gender("masculino").name("Caio Castro").phone("1111111111").rg("325698741").id(id)
				.build();
		assertEquals(expectedCustomer.toString(), customer.toString());
		
	}

}
