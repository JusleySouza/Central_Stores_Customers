package com.central.stores.customers.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.central.stores.customers.test.utils.ClassBuilder;

class ResponseCustomerDTOTest {

	private ResponseCustomerDTO expectedResponseCustomerDTO;
	private AddressDTO addressDTO;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedResponseCustomerDTO = ClassBuilder.responseCustomerDTOBuilder();
		addressDTO = ClassBuilder.addressDTOBuilder();
		expectedResponseCustomerDTO.setId(id);
	}

	@Test
	void builder() {
		ResponseCustomerDTO responseCustomerDTO = ResponseCustomerDTO.builder()
				.id(id)
				.name("Caio Castro")
				.cpf("12365478965")
				.rg("325698741")
				.gender("masculino")
				.phone("1111111111")
				.email("caio@castro.com")
				.address(addressDTO)
				.build();
		assertEquals(expectedResponseCustomerDTO.toString(), responseCustomerDTO.toString());
	}

	@Test
	void setter() {
		ResponseCustomerDTO responseCustomerDTO = new ResponseCustomerDTO();
		responseCustomerDTO.setId(id);
		responseCustomerDTO.setName("Caio Castro");
		responseCustomerDTO.setCpf("12365478965");
		responseCustomerDTO.setRg("325698741");
		responseCustomerDTO.setGender("masculino");
		responseCustomerDTO.setPhone("1111111111");
		responseCustomerDTO.setEmail("caio@castro.com");
		responseCustomerDTO.setAddress(addressDTO);
		assertEquals(expectedResponseCustomerDTO.toString(), responseCustomerDTO.toString());
	}
	
}
