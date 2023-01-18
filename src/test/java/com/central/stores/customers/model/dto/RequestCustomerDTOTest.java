package com.central.stores.customers.model.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.central.stores.customers.test.utils.ClassBuilder;

class RequestCustomerDTOTest {
	
	private RequestCustomerDTO expectedREquestCustomerDTO;

	@BeforeEach
	void setUp() throws Exception {
		expectedREquestCustomerDTO = ClassBuilder.requestCustomerDTOBuilder();
	}
	
	@Test
	void builder() {
		RequestCustomerDTO requestCustomerDTO = RequestCustomerDTO.builder().cpf("12365478965").email("caio@castro.com").gender("masculino")
				.name("Caio Castro").phone("1111111111").rg("325698741").build();
		assertEquals(expectedREquestCustomerDTO.toString(), requestCustomerDTO.toString());
	}

	@Test
	void setter() {
		RequestCustomerDTO requestCostumerDTO = new RequestCustomerDTO();
		requestCostumerDTO.setCpf("12365478965");
		requestCostumerDTO.setEmail("caio@castro.com");
		requestCostumerDTO.setGender("masculino");
		requestCostumerDTO.setName("Caio Castro");
		requestCostumerDTO.setPhone("1111111111");
		requestCostumerDTO.setRg("325698741");
		assertEquals(expectedREquestCustomerDTO.toString(), requestCostumerDTO.toString());
	}

}
