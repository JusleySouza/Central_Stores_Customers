package com.central.stores.customers.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.central.stores.customers.test.utils.ClassBuilder;

class ResponseSumarizedCustomerDTOTest {
	
	private ResponseSumarizedCustomerDTO expectedResponseSumarizedCustomerDTO;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedResponseSumarizedCustomerDTO = ClassBuilder.responseSumarizedCustomerDTOBuilder();
		expectedResponseSumarizedCustomerDTO.setId(id);
	}

	@Test
	void builder() {
		ResponseSumarizedCustomerDTO responseSumarizedCustomerDTO = ResponseSumarizedCustomerDTO.builder()
				.name("Caio Castro")
				.id(id)
				.build();
		assertEquals(expectedResponseSumarizedCustomerDTO.toString(), responseSumarizedCustomerDTO.toString());
	}
	
	@Test
	void setter() {
		ResponseSumarizedCustomerDTO responseSumarizedCustomerDTO = new ResponseSumarizedCustomerDTO();
		responseSumarizedCustomerDTO.setName("Caio Castro");
		responseSumarizedCustomerDTO.setId(id);
		assertEquals(expectedResponseSumarizedCustomerDTO.toString(), responseSumarizedCustomerDTO.toString());
	}
}
