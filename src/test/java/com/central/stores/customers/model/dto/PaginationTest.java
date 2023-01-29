package com.central.stores.customers.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.central.stores.customers.pagination.PaginationDTO;
import com.central.stores.customers.test.utils.ClassBuilder;

class PaginationTest {
	
	private PaginationDTO expectedPagination;

	@BeforeEach
	void setUp() throws Exception {
		expectedPagination = ClassBuilder.paginationBuilder();
	}
	
	@Test
	void builder() {
		PaginationDTO pagination = PaginationDTO.builder()
				.offset(0)
				.pageSize(4)
				.pageNumber(1)
				.moreElements(true)
				.totalPages(6)
				.totalElements(2)
				.build();
		assertEquals(expectedPagination.toString(), pagination.toString());
	}

	@Test
	void setter() {
		PaginationDTO pagination = new PaginationDTO();
		pagination.setOffset(0);
		pagination.setPageSize(4);
		pagination.setPageNumber(1);
		pagination.setMoreElements(true);
		pagination.setTotalPages(6);
	    pagination.setTotalElements(2);
		assertEquals(expectedPagination.toString(), pagination.toString());
	}

}
