package com.central.stores.customers.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.central.stores.customers.pagination.PaginationDTO;
import com.central.stores.customers.test.utils.ClassBuilder;

class ListCustomerTest {
	
	private ListCustomer expectedListCustomer;
	private PaginationDTO pagination;
	private ResponseCustomerDTO responseCustomerDTO;
	private UUID id;

	@BeforeEach
	void setUp() throws Exception {
		id = UUID.randomUUID();
		expectedListCustomer = ClassBuilder.listCustomerBuilder();
		pagination = ClassBuilder.paginationBuilder();
		responseCustomerDTO = ClassBuilder.responseCustomerDTOBuilder();
		responseCustomerDTO.setId(id);
		expectedListCustomer.getContent().get(0).setId(id);
		
	}

	@Test
	void setter() {
		ListCustomer listCustomer = new ListCustomer();
		listCustomer.setPageable(pagination);
		listCustomer.setContent(List.of(responseCustomerDTO));
		assertEquals(expectedListCustomer.toString(), listCustomer.toString());
	}
}
