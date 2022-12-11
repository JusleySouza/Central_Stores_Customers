package com.central.stores.customers.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.central.stores.customers.controller.CustomerController;
import com.central.stores.customers.services.implement.CustomersServicesImplement;

@WebMvcTest(controllers = CustomerController.class)
class CustomersControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomersServicesImplement services;
	
	private final String CONTEXT_PATH = "/customers";
	private final String PATH_FIND_ALL = "/list";
	private final String CUSTOMER_CPF = "12345678987";

	@Test
	void listCustomers() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_ALL))
		.andExpect(status().isOk());
	}
	
	@Test
	void findByCpf() throws Exception{
		mockMvc.perform(get(CONTEXT_PATH + PATH_FIND_ALL + CUSTOMER_CPF))
		.andExpect(status().isOk());
	}
	
	@Test
	void findByNeighborhood() throws Exception {
		mockMvc.perform(get(CONTEXT_PATH).param("neighborhood", "testes"))
		.andExpect(status().isOk());
	}

}
