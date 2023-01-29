package com.central.stores.customers.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.central.stores.customers.test.utils.ClassBuilder;

class ResponseCustomerDTOTest {
	
	private ResponseCustomerDTO expectedResponseCustomerDTO;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedResponseCustomerDTO = ClassBuilder.responseCustomerDTOBuilder();
		expectedResponseCustomerDTO.setId(id);
	}

	@Test
	void builder() {
		ResponseCustomerDTO responseCustomerDTO = ResponseCustomerDTO.builder()
				.name("Caio Castro")
				.id(id)
				.build();
		assertEquals(expectedResponseCustomerDTO.toString(), responseCustomerDTO.toString());
	}
	
	@Test
	void setter() {
		ResponseCustomerDTO responseCustomerDTO = new ResponseCustomerDTO();
		responseCustomerDTO.setName("Caio Castro");
		responseCustomerDTO.setId(id);
		assertEquals(expectedResponseCustomerDTO.toString(), responseCustomerDTO.toString());
	}
}