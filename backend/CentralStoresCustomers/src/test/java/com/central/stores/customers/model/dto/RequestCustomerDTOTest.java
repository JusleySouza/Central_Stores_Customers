package com.central.stores.customers.model.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.central.stores.customers.test.utils.ClassBuilder;

class RequestCustomerDTOTest {
	
	private RequestCustomerDTO expectedREquestCustomerDTO;

	@BeforeEach
	void setUp() throws Exception {
		expectedREquestCustomerDTO = ClassBuilder.requestCustomerDTOBuider();
	}

	@Test
	void builder() {
		RequestCustomerDTO requestCustomerDTO = RequestCustomerDTO.builder().cpf("12365478965").email("caio@castro.com").gender("masculino")
				.name("Caio Castro").phone("1111111111").rg("325698741").build();
		assertEquals(expectedREquestCustomerDTO.toString(), requestCustomerDTO.toString());
	}
	
	

}
