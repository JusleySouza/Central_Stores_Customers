package com.central.stores.customers.model.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.central.stores.customers.test.utils.ClassBuilder;

class AddressDTOTest {
	
	private AddressDTO expectedAddressDTO;

	@BeforeEach
	void setUp() throws Exception {
		expectedAddressDTO = ClassBuilder.addressDTOBuider();
	}

	@Test
	void setter() {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setCity("Bauru");
		addressDTO.setNeighborhood("Pedro Santos Drumond");
		addressDTO.setNumber(56);
		addressDTO.setStreet("Rua das Amelias");
		assertEquals(expectedAddressDTO.toString(), addressDTO.toString());
	}

}
