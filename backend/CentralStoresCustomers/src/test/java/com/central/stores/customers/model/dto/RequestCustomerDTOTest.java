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
